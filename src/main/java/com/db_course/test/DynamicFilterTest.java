package com.db_course.test;

import com.db_course.be.filter.defs.FilterOperation;
import com.db_course.be.filter.entity_filters.impl.CelestialBodyFilter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DynamicFilterTest {

    public static void main(String[] args) {
        testSettingFilters();
        testAddingSortColumns();
        testRemovingFilters();
        testRemovingSortColumns();
    }

    private static void executeQueryAndPrintResults(CelestialBodyFilter filter) {
        try {
            PreparedStatement statement = filter.generatePreparedStatement();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("celestial_body_id") + ", Name: " + resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testSettingFilters() {
        System.out.println("Test: Setting Filters");
        CelestialBodyFilter filter = new CelestialBodyFilter();
        filter.setFilter(CelestialBodyFilter.NAME, FilterOperation.EQUAL, "Earth");
        executeQueryAndPrintResults(filter);
        System.out.println("Expected SQL: SELECT * FROM CELESTIAL_BODIES WHERE CELESTIAL_BODIES.name = ?;");
    }

    private static void testAddingSortColumns() {
        System.out.println("Test: Adding Sort Columns");
        CelestialBodyFilter filter = new CelestialBodyFilter();
        filter.setFilter(CelestialBodyFilter.MASS, FilterOperation.GREATER_THAN, 5.97);
        filter.addSortColumn(CelestialBodyFilter.MASS, false);
        executeQueryAndPrintResults(filter);
        System.out.println("Expected SQL: SELECT * FROM CELESTIAL_BODIES WHERE CELESTIAL_BODIES.mass > ? ORDER BY CELESTIAL_BODIES.mass DESC;");
    }

    private static void testRemovingFilters() {
        System.out.println("Test: Removing Filters");
        CelestialBodyFilter filter = new CelestialBodyFilter();
        filter.setFilter(CelestialBodyFilter.NAME, FilterOperation.EQUAL, "Earth");
        filter.setFilter(CelestialBodyFilter.MASS, FilterOperation.GREATER_THAN, 5.97);
        filter.removeFilter(CelestialBodyFilter.NAME);
        executeQueryAndPrintResults(filter);
        System.out.println("Expected SQL: SELECT * FROM CELESTIAL_BODIES WHERE CELESTIAL_BODIES.mass > ?;");
    }

    private static void testRemovingSortColumns() {
        System.out.println("Test: Removing Sort Columns");
        CelestialBodyFilter filter = new CelestialBodyFilter();
        filter.setFilter(CelestialBodyFilter.MASS, FilterOperation.GREATER_THAN, 5.97);
        filter.addSortColumn(CelestialBodyFilter.MASS, false);
        filter.removeSortColumn(CelestialBodyFilter.MASS);
        executeQueryAndPrintResults(filter);
        System.out.println("Expected SQL: SELECT * FROM CELESTIAL_BODIES WHERE CELESTIAL_BODIES.mass > ?;");
    }
}
