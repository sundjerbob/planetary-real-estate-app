package com.db_course.filter.entity_filters.impl;

import com.db_course.filter.entity_filters.api.EntityFilter;
import com.db_course.filter.nodes.ColumnFilter;
import com.db_course.filter.nodes.FK_ColumnFilter;

import java.time.LocalDate;

public class MissionFilter extends EntityFilter {

    private static final String TABLE = "MISSIONS";
    private static final int COL_NB = 5;

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int LAUNCH_DATE = 2;
    private static final int CELESTIAL_BODY = 3;
    private static final int SPACESHIP = 4;


    protected MissionFilter() {

        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "mission_id", int.class);
        constructors[NAME] = () -> new ColumnFilter(TABLE, "name", String.class);
        constructors[LAUNCH_DATE] = () -> new ColumnFilter(TABLE, "launch_date", LocalDate.class);
        constructors[CELESTIAL_BODY] = () -> new FK_ColumnFilter(
                TABLE,
                "CELESTIAL_BODIES",
                "celestial_body_id",
                "celestial_body_id",
                "name",
                String.class
        );

        constructors[SPACESHIP] = () -> new FK_ColumnFilter(
                TABLE,
                "SPACESHIPS",
                "celestial_body_id",
                "celestial_body_id",
                "name",
                String.class
        );
    }
}
