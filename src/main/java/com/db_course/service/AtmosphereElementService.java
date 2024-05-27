package com.db_course.service;

import com.db_course.dao.AtmosphereElementDao;
import com.db_course.db_config.DB_Client;

public class AtmosphereElementService {


    private static AtmosphereElementService instance;
    private static final Object mutex = new Object();
    private final AtmosphereElementDao atmosphereElementDao;


    private AtmosphereElementService() {
        atmosphereElementDao = new AtmosphereElementDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static AtmosphereElementService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new AtmosphereElementService();
                }
            }
        }
        return instance;
    }
}
