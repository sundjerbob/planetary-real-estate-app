package com.db_course.dao;

import java.sql.Connection;

public class MissionDao {

    private Connection connection;

    public MissionDao(Connection connection) {
        this.connection = connection;
    }
}
