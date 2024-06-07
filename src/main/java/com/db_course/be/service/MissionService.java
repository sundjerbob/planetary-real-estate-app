package com.db_course.be.service;

import com.db_course.be.dao.MissionDao;

public class MissionService {
    private static MissionService instance;
    private static final Object mutex = new Object();
    private final MissionDao celestialPathDao;


    private MissionService() {
        celestialPathDao = new MissionDao();
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
