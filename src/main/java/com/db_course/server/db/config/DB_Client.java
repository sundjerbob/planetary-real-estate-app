package com.db_course.server.db.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB_Client {

    private static Connection connection;

    private static String connectionPrefix = "jdbc:mysql://";

    private static String ipAddress = "localhost";

    private static String dbName = "planetary_real_estate_db";

    private static String port = "3307";

    private static String userName = "root";

    private static String password = "mivanmajer99";

    public static void connect() {

        Properties properties = new Properties();
        properties.put("user", userName);
        properties.put("password", password);
        String connectionPoint = connectionPrefix + ipAddress + ':' + port + '/' + dbName;

        try {
            connection = DriverManager.getConnection(connectionPoint, properties);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");

        }
    }

}
