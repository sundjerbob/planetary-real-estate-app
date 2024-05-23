package com.db_course.db.dao;

import com.db_course.db.entity_model.CelestialBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class CelestialBodyDao {

    private Connection connection;

    public CelestialBodyDao(Connection connection) {
        this.connection = connection;
    }

    public void processAllCelestialBodies(Consumer<CelestialBody> celestialBodyConsumer) throws SQLException {
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

                // Create a CelestialBody object
                CelestialBody celestialBody = new CelestialBody(celestialBodyId, name, type, description);

                // Process the CelestialBody object using the consumer function
                celestialBodyConsumer.accept(celestialBody);
            }
        }
    }
}
