package db;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager(String login, String pswd) throws SQLException {
        myInit(login, pswd);
        conn = OracleConnector.getConnection();
    }
    
    public static void myInit(String login, String pswd) throws SQLException {
        OracleConnector.setUpConnection(//This is where the connection would be
	);
    }

    public Connection getConn() {
        return conn;
    }
}
