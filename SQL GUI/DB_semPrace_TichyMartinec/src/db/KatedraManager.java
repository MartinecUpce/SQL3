/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Fakulta;
import java.sql.Connection;
import entity.Katedra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ROR
 */
public class KatedraManager {
    private Connection conn; 

    public KatedraManager(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Katedra> getKatedraAll() throws SQLException{
        ArrayList<Katedra> katedry = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM db_pracoviste");
        ResultSet rset = stmt.executeQuery();
        
        while (rset.next()) {
            katedry.add(new Katedra(
                    rset.getInt("id_katedry"),
                    rset.getString("nazev_katedry"),
                    rset.getString("zkratka_katedry"),
                    new Fakulta(
                            rset.getInt("id_fakulty"),
                            rset.getString("nazev_fakulty"),
                            rset.getString("zkratka_fakulty")
                    )
            ));
        }
        stmt.close();
        
        return katedry;
    }
    
    public Katedra getKatedraById(int id) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM db_pracoviste WHERE id_katedry = '"+id+"'");
        ResultSet rset = stmt.executeQuery();
        Katedra katedra = null;
        if (rset.next()) {
            katedra = new Katedra(
                    id,
                    rset.getString("nazev_katedry"),
                    rset.getString("zkratka_katedry"),
                    new Fakulta(
                            rset.getInt("id_fakulty"), 
                            rset.getString("nazev_fakulty"),
                            rset.getString("zkratka_fakulty")
                    )
            );
        }
        stmt.close();
        return katedra;
    }

    public void addKatedra(Katedra katedra) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO katedra(nazev_katedry, zkratka_katedry, fakulta_id_fakulty) VALUES (?, ?, ?)");
        stmt.setString(1, katedra.getNazev());
        stmt.setString(2, katedra.getZkratka());
        stmt.setInt(3, katedra.getFakulta().getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public void removeKatedraById(Katedra katedra) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM katedra WHERE id_katedry = ?");
        stmt.setInt(1, katedra.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }

    public void editKatedraById(Katedra katedra) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE katedra SET nazev_katedry = ?, zkratka_katedry = ?, fakulta_id_fakulty = ? WHERE id_katedry = ?");
        stmt.setString(1, katedra.getNazev());
        stmt.setString(2, katedra.getZkratka());
        stmt.setInt(3, katedra.getFakulta().getId());
        stmt.setInt(4, katedra.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }
    
}
