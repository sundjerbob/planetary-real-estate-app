package com.db_course.service;

import com.db_course.dao.AtmosphereElementDao;
import com.db_course.db_config.DB_Client;

public class AtmosphereService {


    private static AtmosphereService instance;
    private static final Object mutex = new Object();
    private final AtmosphereElementDao atmosphereElementDao;


    private AtmosphereService() {
        atmosphereElementDao = new AtmosphereElementDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static AtmosphereService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new AtmosphereService();
                }
            }
        }
        return instance;
    }
}
