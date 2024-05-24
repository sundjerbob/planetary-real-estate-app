package com.db_course.db.service;

import com.db_course.db.config.DB_Client;
import com.db_course.db.dao.CelestialBodyDao;
import com.db_course.db.entity_model.CelestialBody;

import java.util.function.Consumer;

public class CelestialBodyService {

    private static CelestialBodyService instance;
    private static final Object mutex = new Object();
    private final CelestialBodyDao celestialBodyDao;


    private CelestialBodyService() {
        celestialBodyDao = new CelestialBodyDao(DB_Client.getInstance().getConnection());

    }

    public static CelestialBodyService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new CelestialBodyService();
                }
            }
        }
        return instance;
    }



    public void processAllCelestialBodies(Consumer<CelestialBody> consumer) {
        try {
            celestialBodyDao.processAllCelestialBodies(consumer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
