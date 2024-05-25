package com.db_course.service;

import com.db_course.dao.AtmosphereElementDao;
import com.db_course.db_config.DB_Client;

public class AtmosphereElement {


    private static AtmosphereElement instance;
    private static final Object mutex = new Object();
    private final AtmosphereElementDao atmosphereElementDao;


    private AtmosphereElement() {
        atmosphereElementDao = new AtmosphereElementDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static AtmosphereElement getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new AtmosphereElement();
                }
            }
        }
        return instance;
    }
}
