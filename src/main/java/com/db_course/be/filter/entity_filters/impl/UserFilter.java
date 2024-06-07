package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;

public class UserFilter extends EntityFilter {

    private static final String TABLE = "USERS";

    private static final int COL_NB = 4;

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int LAST_NAME = 2;
    private static final int USERNAME = 3;

    public UserFilter() {

        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "user_id", int.class);

        constructors[NAME] = () -> new ColumnFilter(TABLE, "name", String.class);

        constructors[LAST_NAME] = () -> new ColumnFilter(TABLE, "last_name", String.class);

        constructors[USERNAME] = () -> new ColumnFilter(TABLE, "username", String.class);
    }


}
