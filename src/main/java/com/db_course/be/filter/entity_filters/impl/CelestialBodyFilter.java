package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.math.BigDecimal;

public class CelestialBodyFilter extends EntityFilter {


    private static final String TABLE = "CELESTIAL_BODIES";

    private static final int COL_NB = 17;

    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int DESCRIPTION = 2;
    public static final int SURFACE_PRESSURE = 3;
    public static final int SURFACE_TEMPERATURE_MIN = 4;
    public static final int SURFACE_TEMPERATURE_MAX = 5;
    public static final int CORE_TEMPERATURE = 6;
    public static final int EXPLORED = 7;
    public static final int RADIATION_LEVEL = 8;
    public static final int HAS_WATER = 9;
    public static final int SURFACE_AREA = 10;
    public static final int MASS = 11;
    public static final int GRAVITATIONAL_FIELD_HEIGHT = 12;
    public static final int MOVING_SPEED = 13;
    public static final int ROTATION_SPEED = 14;
    public static final int CELESTIAL_BODY_TYPE = 15;
    public static final int ROTATES_AROUND_BODY = 16;


    public CelestialBodyFilter() {

        super(TABLE, COL_NB);

        constructors[ID] = () -> new ColumnFilter(TABLE, "celestial_body_id", int.class);

        constructors[NAME] = () -> new ColumnFilter(TABLE, "name", String.class);

        constructors[DESCRIPTION] = () -> new ColumnFilter(TABLE, "description", String.class);

        constructors[SURFACE_PRESSURE] = () -> new ColumnFilter(TABLE, "surface_pressure", BigDecimal.class);

        constructors[SURFACE_TEMPERATURE_MIN] = () -> new ColumnFilter(TABLE, "surface_temperature_min", BigDecimal.class);

        constructors[SURFACE_TEMPERATURE_MAX] = () -> new ColumnFilter(TABLE, "surface_temperature_max", BigDecimal.class);

        constructors[CORE_TEMPERATURE] = () -> new ColumnFilter(TABLE, "core_temperature", BigDecimal.class);

        constructors[EXPLORED] = () -> new ColumnFilter(TABLE, "explored", Boolean.class);

        constructors[RADIATION_LEVEL] = () -> new ColumnFilter(TABLE, "radiation_level", String.class);

        constructors[HAS_WATER] = () -> new ColumnFilter(TABLE, "has_water", Boolean.class);

        constructors[SURFACE_AREA] = () -> new ColumnFilter(TABLE, "surface_area", BigDecimal.class);

        constructors[MASS] = () -> new ColumnFilter(TABLE, "mass", BigDecimal.class);

        constructors[GRAVITATIONAL_FIELD_HEIGHT] = () -> new ColumnFilter(TABLE, "gravitation_field_height", BigDecimal.class);

        constructors[MOVING_SPEED] = () -> new ColumnFilter(TABLE, "moving_speed", BigDecimal.class);

        constructors[ROTATION_SPEED] = () -> new ColumnFilter(TABLE, "rotation_speed", BigDecimal.class);

        constructors[CELESTIAL_BODY_TYPE] = () -> new FK_ColumnFilter(
                TABLE,
                "CELESTIAL_TYPES",
                "type_id",
                "celestial_type_id",
                "name",
                String.class
        );

        constructors[ROTATES_AROUND_BODY] = () -> new FK_ColumnFilter(
                TABLE,
                TABLE,
                "rotates_around_id",
                "celestial_body_id",
                "name",
                String.class
        );
    }


}
