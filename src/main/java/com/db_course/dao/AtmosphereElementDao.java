package com.db_course.dao;

import com.db_course.entity_model.AtmosphereElement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtmosphereElementDao {

    private Connection connection;

    public AtmosphereElementDao(Connection connection) {
        this.connection = connection;
    }

    public List<AtmosphereElement> getAllAtmosphereElements() throws SQLException {
        List<AtmosphereElement> atmosphereElementList = new ArrayList<>();

        // Prepare SQL query
        String sql = "SELECT atmosphere_id, element_id, percentage FROM ATMOSPHERES_ELEMENTS";

        try (
                // Create PreparedStatement
                PreparedStatement statement = connection.prepareStatement(sql);
                // Execute query and get ResultSet
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve atmosphere element data from the result set
                int atmosphereId = resultSet.getInt("atmosphere_id");
                int elementId = resultSet.getInt("element_id");
                double percentage = resultSet.getDouble("percentage");

                // Create an AtmosphereElement object and add it to the list
                AtmosphereElement atmosphereElement = new AtmosphereElement(atmosphereId, elementId, percentage);
                atmosphereElementList.add(atmosphereElement);
            }
        }

        return atmosphereElementList;
    }
}
