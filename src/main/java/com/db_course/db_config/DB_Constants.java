package com.db_course.db_config;

import java.util.Properties;

public class DB_Constants {

    private static final String CONNECTION_PREFIX = "jdbc:mysql://";
    private static final String IP_ADDR = "localhost";
    private static final String DB_NAME = "planetary_real_estate_db";
    private static final String PORT = "3306";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "mivanmajer99";
    static final String DB_CONNECTION_POINT_URL = CONNECTION_PREFIX + IP_ADDR + ':' + PORT + '/' + DB_NAME;
    static final Properties DB_CONNECTION_PROPERTIES = new Properties();

    static {
        DB_CONNECTION_PROPERTIES.setProperty("user", USER_NAME);
        DB_CONNECTION_PROPERTIES.setProperty("password", PASSWORD);
    }

    private DB_Constants() {
    }
}
