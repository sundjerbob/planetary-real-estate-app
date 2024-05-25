package com.db_course.service;

import com.db_course.dao.CelestialPathDao;
import com.db_course.db_config.DB_Client;

public class ElementService {
    private static CelestialPathService instance;
    private static final Object mutex = new Object();
    private final CelestialPathDao celestialPathDao;


    private CelestialPathService() {
        celestialPathDao = new CelestialPathDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static CelestialPathService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new CelestialPathService();
                }
            }
        }
        return instance;
    }
}
