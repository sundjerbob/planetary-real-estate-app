package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.math.BigDecimal;

public class CelestialPathFilter extends EntityFilter {

    private static final int COL_NB = 5;
    private static final String TABLE = "CELESTIAL_PATHS";

    public static final int ID = 0;
    public static final int DISTANCE_KM = 1;
    public static final int DESCRIPTION = 2;
    public static final int CELESTIAL_BODY_A = 3;
    public static final int CELESTIAL_BODY_B = 4;


    protected CelestialPathFilter() {

        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "path_id", int.class);

        constructors[DISTANCE_KM] = () -> new ColumnFilter(TABLE, "distance_km", BigDecimal.class);

        constructors[DESCRIPTION] = () -> new ColumnFilter(TABLE, "description", String.class);

        constructors[CELESTIAL_BODY_A] = () -> new FK_ColumnFilter(
                TABLE,
                "CELESTIAL_BODIES",
                "body_a_id",
                "celestial_body_id",
                "name",
                String.class
        );

        constructors[CELESTIAL_BODY_B] = () -> new FK_ColumnFilter(
                TABLE,
                "CELESTIAL_BODIES",
                "body_b_id",
                "celestial_body_id",
                "name",
                String.class);
    }

}
