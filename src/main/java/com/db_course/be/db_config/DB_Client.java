package com.db_course.be.db_config;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.db_course.be.db_config.DB_Constants.DB_CONNECTION_POINT_URL;
import static com.db_course.be.db_config.DB_Constants.DB_CONNECTION_PROPERTIES;

public class DB_Client {

    @Getter
    private Connection connection;
    private static volatile DB_Client instance;
    private static final Object mutex = new Object();

    // Changed to non-static
    private void connect() {

        try {
            this.connection = DriverManager.getConnection(DB_CONNECTION_POINT_URL, DB_CONNECTION_PROPERTIES);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
        }
    }

    private DB_Client() {
        connect();
    }

    public void disconnect() {
        try {
            connection.close();
            instance = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static DB_Client getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new DB_Client();
                }
            }
        }
        return instance;
    }


}