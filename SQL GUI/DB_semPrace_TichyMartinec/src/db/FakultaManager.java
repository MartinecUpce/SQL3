/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Fakulta;
import entity.Mistnost;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RoR
 */
public class FakultaManager {
    private Connection conn; 

    public FakultaManager(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Fakulta> getFakultyAll() throws SQLException{;
        ArrayList<Fakulta> fakulty = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fakulta");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            fakulty.add(new Fakulta(
                    rset.getInt("id_fakulty"),
                    rset.getString("nazev_fakulty"),
                    rset.getString("zkratka_fakulty")
            ));
        }
        stmt.close();
        return fakulty;
    }
    
    public void addFakulta(Fakulta fakulta) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO fakulta(nazev_fakulty, zkratka_fakulty) VALUES (?, ?)");
        stmt.setString(1, fakulta.getNazev());
        stmt.setString(2, fakulta.getZkratka());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }
    
    public void removeFakultaById(Fakulta fakulta) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM fakulta WHERE id_fakulty = ?");
        stmt.setInt(1, fakulta.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }
    
    public void editFakultaById(Fakulta fakulta) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("UPDATE fakulta SET nazev_fakulty = ?, zkratka_fakulty = ? WHERE id_fakulty = ?");
        stmt.setString(1, fakulta.getNazev());
        stmt.setString(2, fakulta.getZkratka());
        stmt.setInt(3, fakulta.getId());
        stmt.executeUpdate();
        conn.commit();

        stmt.close();
    }
}
