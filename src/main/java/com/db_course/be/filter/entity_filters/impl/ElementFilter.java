package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;

import java.math.BigDecimal;

public class ElementFilter extends EntityFilter {

    private static final String TABLE = "ELEMENTS";
    private static final int COL_NB = 7;

    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int DESCRIPTION = 2;
    public static final int MIN_PERCENTAGE = 3;
    public static final int MAX_PERCENTAGE = 4;
    public static final int RADIOACTIVE = 5;
    public static final int INERT = 6;


    public ElementFilter() {
        super(TABLE, COL_NB);
        constructors[ID] = () -> new ColumnFilter(TABLE, "element_id", int.class);
        constructors[NAME] = () -> new ColumnFilter(TABLE, "name", String.class);
        constructors[DESCRIPTION] = () -> new ColumnFilter(TABLE, "description", String.class);
        constructors[MIN_PERCENTAGE] = () -> new ColumnFilter(TABLE, "min_percentage", BigDecimal.class);
        constructors[MAX_PERCENTAGE] = () -> new ColumnFilter(TABLE, "max_percentage", BigDecimal.class);
        constructors[RADIOACTIVE] = () -> new ColumnFilter(TABLE, "radioactive", boolean.class);
        constructors[INERT] = () -> new ColumnFilter(TABLE, "inert", boolean.class);
    }


}
