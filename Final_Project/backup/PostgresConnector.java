package com.worker.voteapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnector {
    public Connection connectToPostgres() throws SQLException {
        String url = "jdbc:postgresql://psqldb/mydb"; // Replace with your database URL
        String user = "postgres"; // Replace with your username
        String password = "password"; // Replace with your password

        return DriverManager.getConnection(url, user, password);
    }
}