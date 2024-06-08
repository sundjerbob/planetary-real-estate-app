package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.math.BigDecimal;

public class PropertyFilter extends EntityFilter {

    private static final String TABLE = "PROPERTIES";
    private static final int COL_NB = 9;

    public static final int ID = 0;
    public static final int PROPERTY_REG_NB = 1;
    public static final int ADDRESS = 2;
    public static final int SQUARE_METERS = 3;
    public static final int NAME = 4;
    public static final int DESCRIPTION = 5;
    public static final int PRICE = 6;
    public static final int CELESTIAL_BODY = 7;
    public static final int SOLED_TO_USER = 8;


    public PropertyFilter() {
        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "property_id", int.class);
        constructors[PROPERTY_REG_NB] = () -> new ColumnFilter(TABLE, "property_reg_nb", int.class);
        constructors[ADDRESS] = () -> new ColumnFilter(TABLE, "address", String.class);
        constructors[SQUARE_METERS] = () -> new ColumnFilter(TABLE, "square_meters", BigDecimal.class);
        constructors[NAME] = () -> new ColumnFilter(TABLE, "name", String.class);
        constructors[DESCRIPTION] = () -> new ColumnFilter(TABLE, "description", String.class);
        constructors[PRICE] = () -> new ColumnFilter(TABLE, "price", BigDecimal.class);
        constructors[CELESTIAL_BODY] = () -> new FK_ColumnFilter(
                TABLE,
                "CELESTIAL_BODIES",
                "celestial_body_id",
                "celestial_body_id",
                "name",
                String.class
        );
        constructors[SOLED_TO_USER] = () -> new FK_ColumnFilter(
                TABLE,
                "USERS",
                "sold_to_user_id",
                "user_id",
                "username",
                String.class
        );
    }
}
