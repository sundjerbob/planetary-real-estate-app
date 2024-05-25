package com.db_course.service;

import com.db_course.dao.CelestialPathDao;
import com.db_course.dao.SpaceshipRoomDao;
import com.db_course.db_config.DB_Client;

public class SpaceshipRoomService {
    private static SpaceshipRoomService instance;
    private static final Object mutex = new Object();
    private final SpaceshipRoomDao celestialPathDao;


    private SpaceshipRoomService() {
        celestialPathDao = new SpaceshipRoomDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static SpaceshipRoomService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new SpaceshipRoomService();
                }
            }
        }
        return instance;
    }
}
