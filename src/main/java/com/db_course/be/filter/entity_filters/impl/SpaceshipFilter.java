package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;

import java.math.BigDecimal;

public class SpaceshipFilter extends EntityFilter {

    private static final String TABLE = "SPACESHIPS";
    private static final int COL_NB = 8;

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int MODEL = 2;
    private static final int PASSENGER_CAPACITY = 3;
    private static final int FUEL_CAPACITY = 4;
    private static final int MAX_TRAVEL_RANGE = 5;
    private static final int TRAVELING_SPEED = 6;
    private static final int MANUFACTURER = 7;


    protected SpaceshipFilter() {
        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "spaceship_id", int.class);
        constructors[NAME] = () -> new ColumnFilter(TABLE, "name", String.class);
        constructors[MODEL] = () -> new ColumnFilter(TABLE, "model", String.class);
        constructors[PASSENGER_CAPACITY] = () -> new ColumnFilter(TABLE, "passenger_capacity", int.class);
        constructors[FUEL_CAPACITY] = () -> new ColumnFilter(TABLE, "fuel_capacity", BigDecimal.class);
        constructors[MAX_TRAVEL_RANGE] = () -> new ColumnFilter(TABLE, "max_travel_range", BigDecimal.class);
        constructors[TRAVELING_SPEED] = () -> new ColumnFilter(TABLE, "traveling_speed", BigDecimal.class);
        constructors[MANUFACTURER] = () -> new ColumnFilter(TABLE, "manufacturer", String.class);
    }


}
