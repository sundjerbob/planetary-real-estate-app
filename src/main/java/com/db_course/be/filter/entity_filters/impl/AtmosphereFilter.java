package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.math.BigDecimal;

public class AtmosphereFilter extends EntityFilter {

    private static final String TABLE = "ATMOSPHERES";
    private static final int COL_NB = 6;

    private static final int ID = 0;
    private static final int MAX_TEMPERATURE = 1;
    private static final int MIN_TEMPERATURE = 2;
    private static final int ATMOSPHERE_HEIGHT = 3;
    private static final int ATMOSPHERE_PRESSURE = 4;
    private static final int CELESTIAL_BODY = 6;


    protected AtmosphereFilter() {
        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "atmosphere_id", int.class);

        constructors[MAX_TEMPERATURE] = () -> new ColumnFilter(TABLE, "max_temperature", BigDecimal.class);

        constructors[MIN_TEMPERATURE] = () -> new ColumnFilter(TABLE, "atmosphere_id", BigDecimal.class);

        constructors[ATMOSPHERE_HEIGHT] = () -> new ColumnFilter(TABLE, "atmosphere_id", BigDecimal.class);

        constructors[ATMOSPHERE_PRESSURE] = () -> new ColumnFilter(TABLE, "atmosphere_id", BigDecimal.class);

        constructors[CELESTIAL_BODY] = () -> new FK_ColumnFilter(
                TABLE,
                "CELESTIAL_BODIES",
                "celestial_body_id",
                "celestial_body_id",
                "name",
                String.class
        );
    }


}
