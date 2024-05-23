package com.db_course.server.db.config;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB_Client {

    @Getter
    private Connection connection;

    private String connectionPrefix = "jdbc:mysql://";
    private String ipAddress = "localhost";
    private String dbName = "planetary_real_estate_db";
    private String port = "3306";
    private String userName = "root";
    private String password = "password"; // Change this to your password

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