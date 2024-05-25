package com.db_course.service;

import com.db_course.dao.MissionDao;
import com.db_course.db_config.DB_Client;

public class MissionService {
    private static MissionService instance;
    private static final Object mutex = new Object();
    private final MissionDao celestialPathDao;


    private MissionService() {
        celestialPathDao = new MissionDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static MissionService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new MissionService();
                }
            }
        }
        return instance;
    }
}
