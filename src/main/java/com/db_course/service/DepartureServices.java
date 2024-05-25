package com.db_course.service;

import com.db_course.dao.CelestialPathDao;
import com.db_course.db_config.DB_Client;

public class DepartureServices {

    private static DepartureServices instance;
    private static final Object mutex = new Object();
    private final CelestialPathDao celestialPathDao;


    private DepartureServices() {
        celestialPathDao = new CelestialPathDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static DepartureServices getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new DepartureServices();
                }
            }
        }
        return instance;
    }
}
