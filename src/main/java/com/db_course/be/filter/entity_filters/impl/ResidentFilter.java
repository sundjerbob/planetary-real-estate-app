package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;

public class ResidentFilter extends EntityFilter {


    private static final String TABLE = "RESIDENTS";
    private static final int COL_NB = 5;

    private static final int ID = 0;
    private static final int FULL_NAME = 1;
    private static final int GENDER = 2;
    private static final int BIRTH_DATE = 3;
    private static final int DEATH_DATE = 4;


    public ResidentFilter() {
        super(TABLE, COL_NB);
        constructors[ID] = () -> new ColumnFilter(TABLE, "resident_id", int.class);
        constructors[FULL_NAME] = () -> new ColumnFilter(TABLE, "full_name", String.class);
        constructors[GENDER] = () -> new ColumnFilter(TABLE, "resident_id", int.class);
        constructors[BIRTH_DATE] = () -> new ColumnFilter(TABLE, "resident_id", int.class);
        constructors[DEATH_DATE] = () -> new ColumnFilter(TABLE, "resident_id", int.class);

    }
}
