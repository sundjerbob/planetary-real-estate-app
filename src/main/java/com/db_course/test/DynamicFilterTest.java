package com.db_course.test;

import com.db_course.filter.defs.FilterOperation;
import com.db_course.filter.entity_filters.impl.CelestialBodyFilter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DynamicFilterTest {

    public static void main(String[] args) {
        try {
            testNormalAttributeFiltering();
            testForeignKeyFieldFiltering();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testNormalAttributeFiltering() throws SQLException {
        CelestialBodyFilter filter = new CelestialBodyFilter();
        filter.setFilter(CelestialBodyFilter.NAME, FilterOperation.EQUAL, "Earth");

        PreparedStatement ps = filter.generatePreparedStatement();

        System.out.println("Generated PreparedStatement after parameterization: " + ps.toString());

        executeAndPrintResults(ps);
    }

    public static void testForeignKeyFieldFiltering() throws SQLException {
        CelestialBodyFilter filter = new CelestialBodyFilter();
        filter.setFilter(CelestialBodyFilter.CELESTIAL_BODY_TYPE, FilterOperation.EQUAL, "Planet");

        filter.setFilter(CelestialBodyFilter.DESCRIPTION, FilterOperation.STARTS_WITH, "kita");

        PreparedStatement ps = filter.generatePreparedStatement();
        executeAndPrintResults(ps);
    }

    private static void executeAndPrintResults(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        }
    }
}
