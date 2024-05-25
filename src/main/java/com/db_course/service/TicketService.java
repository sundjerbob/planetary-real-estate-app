package com.db_course.service;

import com.db_course.dao.TicketDao;
import com.db_course.db_config.DB_Client;

public class TicketService {
    private static TicketService instance;
    private static final Object mutex = new Object();
    private final TicketDao celestialPathDao;


    private TicketService() {
        celestialPathDao = new TicketDao(
                DB_Client.getInstance().getConnection()
        );
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
