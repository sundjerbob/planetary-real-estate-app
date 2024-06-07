package com.db_course.be.service;

import com.db_course.be.dao.SpaceshipRoomDao;

public class SpaceshipRoomService {
    private static SpaceshipRoomService instance;
    private static final Object mutex = new Object();
    private final SpaceshipRoomDao celestialPathDao;


    private SpaceshipRoomService() {
        celestialPathDao = new SpaceshipRoomDao();
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
