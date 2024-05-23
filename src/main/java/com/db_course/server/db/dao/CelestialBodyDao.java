package com.db_course.server.db.dao;

import com.db_course.server.db.entity_model.CelestialBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelestialBodyDao {

    private Connection connection;

    public CelestialBodyDao(Connection connection) {
        this.connection = connection;
    }

    public List<CelestialBody> getAllCelestialBodies() throws SQLException {
        List<CelestialBody> celestialBodyList = new ArrayList<>();

        // Prepare SQL query
        String sql = "SELECT celestial_body_id, name, type, description FROM CELESTIAL_BODIES";

        try (
                // Create PreparedStatement
                PreparedStatement statement = connection.prepareStatement(sql);
                // Execute query and get ResultSet
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve celestial body data from the result set
                int celestialBodyId = resultSet.getInt("celestial_body_id");
                String name = resultSet.getString("name");
                int type = resultSet.getInt("type");
                String description = resultSet.getString("description");

                // Create a CelestialBody object and add it to the list
                CelestialBody celestialBody = new CelestialBody(celestialBodyId, name, type, description);
                celestialBodyList.add(celestialBody);
            }
        }

        return celestialBodyList;
    }
}
