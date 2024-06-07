package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.math.BigDecimal;

public class TicketFilter extends EntityFilter {

    private static final String TABLE = "TICKETS";
    private static final int COL_NB = 7;

    public static final int ID = 0;
    public static final int PRICE = 1;
    public static final int ROOM_NUMBER = 2;
    public static final int SOLD = 3;
    public static final int PASSENGER_ID = 4;
    public static final int DEPARTURE_ID = 5;
    public static final int SPACESHIP = 6;

    public TicketFilter() {

        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "ticket_id", int.class);

        constructors[PRICE] = () -> new ColumnFilter(TABLE, "price", BigDecimal.class);

        constructors[ROOM_NUMBER] = () -> new ColumnFilter(TABLE, "room_number", int.class);

        constructors[SOLD] = () -> new ColumnFilter(TABLE, "sold", Boolean.class);

        constructors[PASSENGER_ID] = () -> new FK_ColumnFilter(
                TABLE,
                "USERS",
                "passenger_id",
                "user_id",
                "user_id",
                int.class
        );

        constructors[DEPARTURE_ID] = () -> new FK_ColumnFilter(
                TABLE,
                "DEPARTURES",
                "departure_id",
                "departure_id",
                "departure_id",
                int.class
        );

    }
}
