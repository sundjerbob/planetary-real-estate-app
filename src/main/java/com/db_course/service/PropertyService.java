package com.db_course.service;

import com.db_course.dao.PropertyDao;
import com.db_course.db_config.DB_Client;

public class PropertyService {

    private static PropertyService instance;
    private static final Object mutex = new Object();
    private final PropertyDao propertyDao;


    private PropertyService() {
        propertyDao = new PropertyDao();

    }

    public static PropertyService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new PropertyService();
                }
            }
        }
        return instance;
    }

}
