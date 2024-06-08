package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.time.LocalDate;

public class MissionFilter extends EntityFilter {

    private static final String TABLE = "MISSIONS";
    private static final int COL_NB = 8;

    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int DESCRIPTION = 2;
    public static final int START_DATE = 3;
    public static final int END_DATE = 4;
    public static final int COMPLETED = 5;

    public static final int CELESTIAL_BODY = 6;
    public static final int SPACESHIP = 7;


    public MissionFilter() {

        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "mission_id", int.class);
        constructors[NAME] = () -> new ColumnFilter(TABLE, "name", String.class);
        constructors[DESCRIPTION] = () -> new ColumnFilter(TABLE, "description", String.class);
        constructors[START_DATE] = () -> new ColumnFilter(TABLE, "start_date", LocalDate.class);
        constructors[END_DATE] = () -> new ColumnFilter(TABLE, "end_date", LocalDate.class);
        constructors[COMPLETED] = () -> new ColumnFilter(TABLE, "description", boolean.class);

        constructors[CELESTIAL_BODY] = () -> new FK_ColumnFilter(
                TABLE,
                "CELESTIAL_BODIES",
                "explored_body_id",
                "celestial_body_id",
                "name",
                String.class
        );

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
