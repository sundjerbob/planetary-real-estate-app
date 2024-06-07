package com.db_course.be.service;

import com.db_course.be.dao.TicketDao;

public class TicketService {
    private static TicketService instance;
    private static final Object mutex = new Object();
    private final TicketDao celestialPathDao;


    private TicketService() {
        celestialPathDao = new TicketDao();
    }


    public static TicketService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new TicketService();
                }
            }
        }
        return instance;
    }
}
