package com.db_course.dao;

import java.sql.Connection;

public class PropertyDao {


    private final Connection connection;

    public PropertyDao(Connection connection) {
        this.connection = connection;
    }
}
