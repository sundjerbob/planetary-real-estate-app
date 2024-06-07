package com.db_course.be.filter.entity_filters.impl;

import com.db_course.be.filter.entity_filters.api.EntityFilter;
import com.db_course.be.filter.nodes.MetaDataNode;
import com.db_course.be.filter.nodes.MetaNodeConstructor;
import com.db_course.be.filter.nodes.ColumnFilter;
import com.db_course.be.filter.nodes.FK_ColumnFilter;

import java.time.LocalDate;

public class CelestialBodyResidentFilter extends EntityFilter {


    private static final String TABLE = "CELESTIAL_BODY_RESIDENTS";
    private static final int COL_NB = 5;


    public static final int ID = 0;
    public static final int RESIDENT_FROM = 1;
    public static final int RESIDENT_UNTIL = 2;
    public static final int RESIDENT_ID = 3;
    public static final int CELESTIAL_BODY_ID = 4;


    public CelestialBodyResidentFilter() {

        super(TABLE, COL_NB);

        filters = new MetaDataNode[COL_NB];

        constructors = new MetaNodeConstructor[COL_NB];

        constructors[ID] = () -> new ColumnFilter(TABLE, "id", int.class);

        constructors[RESIDENT_FROM] = () -> new ColumnFilter(TABLE, "resident_until", LocalDate.class);

        constructors[RESIDENT_UNTIL] = () -> new ColumnFilter(TABLE, "resident_from", LocalDate.class);

        constructors[CELESTIAL_BODY_ID] = () -> new FK_ColumnFilter(
                TABLE,
                "CELESTIAL_BODIES",
                "celestial_body_id",
                "celestial_body_id",
                "name",
                String.class);

        constructors[RESIDENT_ID] = () -> new FK_ColumnFilter(
                TABLE,
                "RESIDENTS",
                "resident_id",
                "resident_id",
                "full_name",
                String.class);
    }


}
