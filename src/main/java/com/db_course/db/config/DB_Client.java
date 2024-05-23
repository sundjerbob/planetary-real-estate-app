package com.db_course.db.config;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB_Client {

    @Getter
    private Connection connection;

    private final String connectionPrefix = "jdbc:mysql://";
    private final String ipAddress = "localhost";
    private final String dbName = "planetary_real_estate_db";
    private final String port = "3306";
    private final String userName = "root";
    private final String password = "mivanmajer99"; // Change this to your password

    // Changed to non-static
    private void connect() {
        Properties properties = new Properties();
        properties.put("user", userName);
        properties.put("password", password);
        String connectionPoint = connectionPrefix + ipAddress + ':' + port + '/' + dbName;

        try {
            this.connection = DriverManager.getConnection(connectionPoint, properties);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
        }
    }

    private DB_Client() {
        connect();
    }

    private static final class SingleInstanceHolder {
        private static final DB_Client singleInstance = new DB_Client();
    }

    // Thread safe implementation of singleton
    public static DB_Client getInstance() {
        return SingleInstanceHolder.singleInstance;
    }

}