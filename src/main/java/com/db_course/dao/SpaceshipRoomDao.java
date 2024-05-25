package com.db_course.dao;

import java.sql.Connection;

public class SpaceshipRoomDao {

    Connection connection;

    public SpaceshipRoomDao(Connection connection) {

        this.connection = connection;
    }
}
