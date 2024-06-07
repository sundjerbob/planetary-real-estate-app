package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;

public class DepartureFilter extends EntityFilter {

    private static final String TABLE = "DEPARTURES";
    private static final int COL_NB = 5;

    public static final int ID = 0;
    public static final int DEPARTURE_DATETIME = 0;
    public static final int TRAVEL_DURATION_DAYS = 1;
    public static final int SPACESHIP = 2;
    public static final int CELESTIAL_ORIGIN = 3;
    public static final int CELESTIAL_DESTINATION = 4;


    protected DepartureFilter(String table, int colNb) {
        super(table, colNb);
    }


}
