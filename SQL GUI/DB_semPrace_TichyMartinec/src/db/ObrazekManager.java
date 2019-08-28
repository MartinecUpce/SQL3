/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Obrazek;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.Image;

/**
 *
 * @author ROR
 */
public class ObrazekManager {
    private Connection conn;

    public ObrazekManager(Connection conn) {
        this.conn = conn;
    }
    
    public Obrazek getObrazekByUserId(int id) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tab_obraz WHERE uzivatel_id_uzivatel = "+id);
        ResultSet rset = stmt.executeQuery();
        Obrazek obrazek = null;
        if (rset.next()) {
            Blob blob = rset.getBlob("obsah");
            obrazek = new Obrazek(
                    rset.getInt("id_obraz"),
                    rset.getString("typ"),
                    rset.getString("pripona"),
                    rset.getDate("datum_pridani"),
                    //new Image(new ByteArrayInputStream(blob.getBytes(1, (int)blob.length())))
                    new ByteArrayInputStream(blob.getBytes(1, (int)blob.length()))
            );
        }
        stmt.close();
        return obrazek;
    }
}
