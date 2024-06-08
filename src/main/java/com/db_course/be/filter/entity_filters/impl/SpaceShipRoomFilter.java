package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

public class SpaceShipRoomFilter extends EntityFilter {

    private static final String TABLE = "SPACESHIP_ROOM";
    private static final int COL_NB = 5;

    public static final int ID = 0;
    public static final int PERKS = 1;
    public static final int HIBERNATION_CAPSULES_NB = 2;
    public static final int MAX_CAPACITY = 3;
    public static final int SPACESHIP = 4;


    public SpaceShipRoomFilter() {
        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "room_id", int.class);

        constructors[PERKS] = () -> new ColumnFilter(TABLE, "perks", String.class);

        constructors[HIBERNATION_CAPSULES_NB] = () -> new ColumnFilter(TABLE, "hibernation_capsules_nb", int.class);

        constructors[MAX_CAPACITY] = () -> new ColumnFilter(TABLE, "max_capacity", int.class);

        constructors[SPACESHIP] = () -> new FK_ColumnFilter(
                TABLE,
                "SPACESHIPS",
                "spaceship_id",
                "spaceship_id",
                "name",
                String.class
        );

    }
}
