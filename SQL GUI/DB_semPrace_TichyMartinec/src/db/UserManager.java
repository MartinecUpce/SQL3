/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.User;
import entity.Vyucujici;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import libs.AlertWindow;

/**
 *
 * @author ROR
 */
public class UserManager {

    private Connection conn;
    private User loggedUser;

    public UserManager(Connection conn) {
        this.conn = conn;
        this.loggedUser = null;
    }

    public boolean login(String username, String password) throws SQLException {
        //PreparedStatement stmt = conn.prepareStatement("SELECT id_uzivatel, username, password, id_role, nazev FROM uzivatel JOIN role_admin ON role_id = id_role WHERE username = '"+username+"' AND password = '"+password+"'");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM uzivatel_ucitel2 WHERE username = '" + username + "' AND password = '" + password + "'");
        ResultSet rset = stmt.executeQuery();
        KatedraManager katedraManager = new KatedraManager(conn);
        ObrazekManager obrazekManager = new ObrazekManager(conn);
        VyucujiciManager vyucujiciManager = new VyucujiciManager(conn);

        if (rset.next()) {
            this.loggedUser = new User(
                    rset.getInt("id_uzivatel"),
                    rset.getString("username"),
                    rset.getString("password"),
                    rset.getString("nazev"),
                    obrazekManager.getObrazekByUserId(rset.getInt("id_uzivatel")),
                    vyucujiciManager.getVyucujiciById(rset.getInt("vyucujici_id"))
            );
            stmt.close();
            return true;
        } else {
            stmt = conn.prepareStatement("SELECT * FROM uzivatel JOIN role_admin ON role_id = id_role WHERE username = '" + username + "' AND password = '" + password + "'");
            rset = stmt.executeQuery();
            if (rset.next()) {
                this.loggedUser = new User(
                        rset.getInt("id_uzivatel"),
                        rset.getString("username"),
                        rset.getString("password"),
                        rset.getString("nazev"),
                        obrazekManager.getObrazekByUserId(rset.getInt("id_uzivatel")),
                        null
                );
                stmt.close();
                return true;
            }
            stmt.close();
            return false;
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public ArrayList<User> getUserAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        ObrazekManager obrazekManager = new ObrazekManager(conn);
        KatedraManager katedraManager = new KatedraManager(conn);
        VyucujiciManager vyucujiciManager = new VyucujiciManager(conn);
        Vyucujici vyucujici;

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM uzivatel_role");
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            if (vyucujiciManager.getVyucujiciById(rset.getInt("vyucujici_id")) != null) {
                vyucujici = vyucujiciManager.getVyucujiciById(rset.getInt("vyucujici_id"));
            } else {
                vyucujici = null;
            }
            users.add(new User(
                    rset.getInt("id_uzivatel"),
                    rset.getString("username"),
                    rset.getString("password"),
                    rset.getString("nazev"),
                    obrazekManager.getObrazekByUserId(rset.getInt("id_uzivatel")),
                    vyucujici = vyucujiciManager.getVyucujiciById(rset.getInt("vyucujici_id"))
            )
            );
        }
        stmt.close();
        return users;
    }

    public int addUser(User user) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{? = call func_vloz_user(?, ?, ?, ? )}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
        stmt.setString(2, user.getJmeno());
        stmt.setString(3, user.getHeslo());
        stmt.setString(4, user.getRole());
        if (user.getVyucujici() != null) {
            stmt.setInt(5, user.getVyucujici().getId());
        } else {
            stmt.setInt(5, 0);
        }

        stmt.executeUpdate();
        int userId = stmt.getInt(1);
        conn.commit();
        if (userId == -1) {
            return userId;
        }

        if (user.getObrazek() != null) {
            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO tab_obraz(typ, pripona, datum_pridani, obsah, uzivatel_id_uzivatel) VALUES (?, ?, ?, ?, ?)");
            stmt2.setString(1, user.getObrazek().getTyp());
            stmt2.setString(2, user.getObrazek().getPripona());
            stmt2.setDate(3, new java.sql.Date(user.getObrazek().getDatum().getTime()));
            stmt2.setBinaryStream(4, user.getObrazek().getObrazek());
            stmt2.setInt(5, userId);

            stmt2.executeUpdate();
            conn.commit();
            stmt2.close();
        }

        stmt.close();

        return userId;
    }

    public void removeUserById(User user) throws SQLException {
        PreparedStatement stmt;
        if (user.getObrazek() != null) {
            stmt = conn.prepareStatement("DELETE FROM tab_obraz WHERE id_obraz = ?");
            stmt.setInt(1, user.getObrazek().getId());
            stmt.executeUpdate();
            conn.commit();
        }

        stmt = conn.prepareStatement("DELETE FROM uzivatel WHERE id_uzivatel = ?");
        stmt.setInt(1, user.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();

    }

    public String editUserById(User user) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{? = call func_update_user(?, ?, ?, ?, ?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.setInt(2, user.getId());
        stmt.setString(3, user.getJmeno());
        stmt.setString(4, user.getHeslo());
        stmt.setString(5, user.getRole());
        stmt.setInt(6, user.getVyucujici().getId());
        System.out.println(user.getId() + ", " + user.getJmeno() + ", " + user.getHeslo() + ", " + user.getRole() + ", " + user.getVyucujici().getId());
        stmt.executeUpdate();
        String message = stmt.getString(1);
        conn.commit();
        stmt.close();

        if (user.getObrazek() != null) {
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM tab_obraz WHERE uzivatel_id_uzivatel = ?");
            stmt2.setInt(1, user.getId());
            stmt2.executeUpdate();
            stmt2 = conn.prepareStatement("INSERT INTO tab_obraz(typ, pripona, datum_pridani, obsah, uzivatel_id_uzivatel) VALUES (?, ?, ?, ?, ?)");
            stmt2.setString(1, user.getObrazek().getTyp());
            stmt2.setString(2, user.getObrazek().getPripona());
            stmt2.setDate(3, new java.sql.Date(user.getObrazek().getDatum().getTime()));
            stmt2.setBinaryStream(4, user.getObrazek().getObrazek());
            stmt2.setInt(5, user.getId());

            stmt2.executeUpdate();
            conn.commit();
            stmt2.close();
        }

        return message;
    }
}
