package com.wallet.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConf {
    private Connection connection;
    public DatabaseConf(){
        String jdbcurl = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");
        try {
            connection = DriverManager.getConnection(jdbcurl, username, password);
            if (connection != null) {
                System.out.println("Database connection established!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to database.", e);
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
