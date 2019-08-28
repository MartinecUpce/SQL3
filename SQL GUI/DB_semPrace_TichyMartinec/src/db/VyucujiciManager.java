/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Fakulta;
import entity.Katedra;
import entity.Obor;
import entity.Plan;
import entity.Predmet;
import entity.Vyucujici;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ROR
 */
public class VyucujiciManager {
    private Connection conn; 

    public VyucujiciManager(Connection conn) {
        this.conn = conn;
    }
    
    public Vyucujici getVyucujiciById(int id) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_uc_kat_fak WHERE id_vyucujici = "+id);
        ResultSet rset = stmt.executeQuery();
        Vyucujici vyucujici = null;
        if (rset.next()) {
            vyucujici = new Vyucujici(
                    rset.getInt("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("email"),
                    rset.getString("telefon"),
                    new Katedra(
                            rset.getInt("id_katedry"),
                            rset.getString("nazev_katedry"),
                            rset.getString("zkratka_katedry"),
                            new Fakulta(
                                    rset.getInt("id_fakulty"),
                                    rset.getString("nazev_fakulty"),
                                    rset.getString("zkratka_fakulty")
                            )
                    )
            );
        }
        stmt.close();
        return vyucujici;
    }
    
    public ArrayList<Vyucujici> getVyucujiciAll() throws SQLException{
        ArrayList<Vyucujici> vyucujici = new ArrayList<>();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_uc_kat_fak");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            vyucujici.add(new Vyucujici(
                    rset.getInt("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("email"),
                    rset.getString("telefon"),
                    new Katedra(
                            rset.getInt("id_katedry"),
                            rset.getString("nazev_katedry"),
                            rset.getString("zkratka_katedry"),
                            new Fakulta(
                                    rset.getInt("id_fakulty"),
                                    rset.getString("nazev_fakulty"),
                                    rset.getString("zkratka_fakulty")
                            )
                    )
            ));
        }
        stmt.close();
        return vyucujici;
    }
    
    public ArrayList<Vyucujici> getVyucujiciByPredmetAll(int id) throws SQLException{
        ArrayList<Vyucujici> vyucujici = new ArrayList<>();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_ucitel_uci2 WHERE id_prob = "+id);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            vyucujici.add(new Vyucujici(
                    rset.getInt("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("email"),
                    rset.getString("telefon"),
                    new Katedra(
                            rset.getInt("id_katedry"),
                            rset.getString("nazev_katedry"),
                            rset.getString("zkratka_katedry"),
                            new Fakulta(
                                    rset.getInt("id_fakulty"),
                                    rset.getString("nazev_fakulty"),
                                    rset.getString("zkratka_fakulty")
                            )
                    )
            ));
        }
        stmt.close();
        return vyucujici;
    }
    

    public void addVyucujici(Vyucujici vyucujici) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO vyucujici(jmeno, prijmeni, email, telefon, katedra_id_katedry) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, vyucujici.getJmeno());
        stmt.setString(2, vyucujici.getPrijmeni());
        stmt.setString(3, vyucujici.getEmail());
        stmt.setString(4, vyucujici.getTelefon());
        stmt.setInt(5, vyucujici.getKatedra().getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public void removeVyucujiciById(Vyucujici vyucujici) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM vyucujici WHERE id_vyucujici = ?");
        stmt.setInt(1, vyucujici.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public void editVyucujiciById(Vyucujici vyucujici) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE vyucujici SET jmeno = ?, prijmeni = ?, email = ?, telefon = ?, katedra_id_katedry = ? WHERE id_vyucujici = ?");
        stmt.setString(1, vyucujici.getJmeno());
        stmt.setString(2, vyucujici.getPrijmeni());
        stmt.setString(3, vyucujici.getEmail());
        stmt.setString(4, vyucujici.getTelefon());
        stmt.setInt(5, vyucujici.getKatedra().getId());
        stmt.setInt(6, vyucujici.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }
}
