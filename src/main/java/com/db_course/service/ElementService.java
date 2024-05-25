package com.db_course.service;

import com.db_course.dao.CelestialPathDao;
import com.db_course.dao.ElementDao;
import com.db_course.db_config.DB_Client;

public class ElementService {

    private static ElementService instance;
    private static final Object mutex = new Object();
    private final ElementDao celestialPathDao;


    private ElementService() {
        celestialPathDao = new ElementDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static ElementService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new ElementService();
                }
            }
        }
        return instance;
    }
}
