/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.util.ArrayList;
import entity.Obor;
import entity.Obrazek;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.Image;

/**
 *
 * @author RoR
 */
public class OborManager {
    private Connection conn; 

    public OborManager(Connection conn) {
        this.conn = conn;
    }
    
    public Obor getOborById(int id) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_obor WHERE id_stud_oboru = "+id);
        ResultSet rset = stmt.executeQuery();
        Obor obor = null;
        if (rset.next()) {
            obor = new Obor(
                    rset.getInt("id_stud_oboru"),
                    rset.getString("jmeno_oboru"),
                    rset.getInt("odhad_studentu"),
                    rset.getString("nazev_formy")
            );
        }
        stmt.close();
        return obor;
    }
    
    public ArrayList<Obor> getOborAll() throws SQLException{
        ArrayList<Obor> obory = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_obor");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            obory.add(new Obor(
                    rset.getInt("id_stud_oboru"),
                    rset.getString("jmeno_oboru"),
                    rset.getInt("odhad_studentu"),
                    rset.getString("nazev_formy")
            ));
        }
        stmt.close();
        return obory;
    }

    public void addObor(Obor obor) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{call proc_vloz_obor(?,?,?)}");
        stmt.setInt(1, obor.getOdhad());
        stmt.setString(2, obor.getNazev());
        stmt.setString(3, obor.getForma());
        stmt.execute();
        conn.commit();

        stmt.close();
    }

    public void removeOborById(Obor obor) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM studijni_obor WHERE id_stud_oboru = ?");
        stmt.setInt(1, obor.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public void editOborById(Obor obor) throws SQLException {
        CallableStatement stmt = conn.prepareCall("{call proc_update_obor(?,?,?,?)}");
        stmt.setInt(1, obor.getId());
        stmt.setInt(2, obor.getOdhad());
        stmt.setString(3, obor.getNazev());
        stmt.setString(4, obor.getForma());
        stmt.execute();
        conn.commit();

        stmt.close();
    }
}
