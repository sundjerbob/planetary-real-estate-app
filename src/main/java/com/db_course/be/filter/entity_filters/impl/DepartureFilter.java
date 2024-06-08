package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.time.LocalDateTime;

public class DepartureFilter extends EntityFilter {

    private static final String TABLE = "DEPARTURES";
    private static final int COL_NB = 5;

    public static final int ID = 0;
    public static final int DEPARTURE_DATETIME = 1;
    public static final int SPACESHIP = 2;
    public static final int CELESTIAL_ORIGIN = 3;
    public static final int CELESTIAL_DESTINATION = 4;

    public DepartureFilter() {
        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "departure_id", int.class);
        constructors[DEPARTURE_DATETIME] = () -> new ColumnFilter(TABLE, "departure_datetime", LocalDateTime.class);
        constructors[SPACESHIP] = () -> new FK_ColumnFilter(TABLE, "SPACESHIPS", "spaceship_id", "spaceship_id", "name", String.class);
        constructors[CELESTIAL_ORIGIN] = () -> new FK_ColumnFilter(TABLE, "CELESTIAL_BODIES", "celestial_origin_id", "celestial_body_id", "name", String.class);
        constructors[CELESTIAL_DESTINATION] = () -> new FK_ColumnFilter(TABLE, "CELESTIAL_BODIES", "celestial_destination_id", "celestial_body_id", "name", String.class);
    }
}
