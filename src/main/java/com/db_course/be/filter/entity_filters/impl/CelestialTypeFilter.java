package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;

public class CelestialTypeFilter extends EntityFilter {

    private static final String TABLE = "CELESTIAL_TYPES";
    private static final int COL_NB = 3;

    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int DESCRIPTION = 2;

    public CelestialTypeFilter() {
        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "celestial_type_id", int.class);

        constructors[NAME] = () -> new ColumnFilter(TABLE, "name", String.class);

        constructors[DESCRIPTION] = () -> new ColumnFilter(TABLE, "description", String.class);
    }
}
