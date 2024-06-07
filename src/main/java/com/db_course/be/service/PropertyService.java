package com.db_course.be.service;

import com.db_course.be.dao.PropertyDao;

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
