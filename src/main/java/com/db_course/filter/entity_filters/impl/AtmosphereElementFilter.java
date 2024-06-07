package com.db_course.filter.entity_filters.impl;

import com.db_course.filter.entity_filters.api.EntityFilter;
import com.db_course.filter.nodes.ColumnFilter;
import com.db_course.filter.nodes.FK_ColumnFilter;

import java.math.BigDecimal;

public class AtmosphereElementFilter extends EntityFilter {

    private static final String TABLE = "ATMOSPHERES_ELEMENTS";
    private static final int COL_NB = 3;

    public static final int PERCENTAGE = 0;
    public static final int ELEMENT = 1;
    public static final int ATMOSPHERE_ID = 2;


    protected AtmosphereElementFilter() {

        super(TABLE, COL_NB);

        constructors[PERCENTAGE] = () -> new ColumnFilter(TABLE, "percentage", BigDecimal.class);

        constructors[ELEMENT] = () -> new FK_ColumnFilter(
                TABLE,
                "ELEMENTS",
                "element_id",
                "element_id",
                "name",
                String.class
        );

        constructors[ATMOSPHERE_ID] = () -> new FK_ColumnFilter(
                TABLE,
                "ATMOSPHERES",
                "atmosphere_id",
                "atmosphere_id",
                "atmosphere_id",
                int.class
        );
    }

}
