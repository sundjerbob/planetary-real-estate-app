package com.db_course.db_config;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.db_course.db_config.Constants.*;

public class DB_Client {

    @Getter
    private Connection connection;


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

    private static final class SingleInstanceHolder {
        private static final DB_Client singleInstance = new DB_Client();
    }

    // Thread safe implementation of singleton
    public static DB_Client getInstance() {
        return SingleInstanceHolder.singleInstance;
    }

}