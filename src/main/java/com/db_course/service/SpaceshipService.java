package com.db_course.service;

import com.db_course.dao.SpaceshipDao;
import com.db_course.db_config.DB_Client;

public class SpaceshipService {
    private static SpaceshipService instance;
    private static final Object mutex = new Object();
    private final SpaceshipDao celestialPathDao;


    private SpaceshipService() {
        celestialPathDao = new SpaceshipDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static SpaceshipService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new SpaceshipService();
                }
            }
        }
        return instance;
    }

}
