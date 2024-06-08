package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.math.BigDecimal;

public class AtmosphereFilter extends EntityFilter {

    private static final String TABLE = "ATMOSPHERES";
    private static final int COL_NB = 7;

    public static final int ID = 0;
    public static final int MAX_TEMPERATURE = 1;
    public static final int MIN_TEMPERATURE = 2;
    public static final int ATMOSPHERE_HEIGHT = 3;
    public static final int ATMOSPHERE_PRESSURE = 4;
    public static final int CELESTIAL_BODY = 6;


    public AtmosphereFilter() {

        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "atmosphere_id", int.class);

        constructors[MAX_TEMPERATURE] = () -> new ColumnFilter(TABLE, "max_temperature", BigDecimal.class);

        constructors[MIN_TEMPERATURE] = () -> new ColumnFilter(TABLE, "min_temperature", BigDecimal.class);

        constructors[ATMOSPHERE_HEIGHT] = () -> new ColumnFilter(TABLE, "atmosphere_height", BigDecimal.class);

        constructors[ATMOSPHERE_PRESSURE] = () -> new ColumnFilter(TABLE, "ampere_pressure", BigDecimal.class);

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
