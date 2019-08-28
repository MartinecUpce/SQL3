/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;

/**
 *
 * @author ROR
 */
public class PracovisteManager {
    private Connection conn; 

    public PracovisteManager(Connection conn) {
        this.conn = conn;
    }
}
