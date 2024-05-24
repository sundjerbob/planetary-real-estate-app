package com.db_course.db.dao;

import com.db_course.db.entity_model.CelestialBody;
import com.db_course.db.entity_model.CelestialType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class CelestialBodyDao {

    private final Connection connection;

    public CelestialBodyDao(Connection connection) {
        this.connection = connection;
    }

    public void processAllCelestialBodies(Consumer<CelestialBody> celestialBodyConsumer) throws SQLException {
        String sql = "SELECT * FROM CELESTIAL_BODIES";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                celestialBodyConsumer.accept(mapToCelestialBody(resultSet));
            }
        }
    }

    public void processCelestialBodiesByType(CelestialType type, Consumer<CelestialBody> celestialBodyConsumer) throws SQLException {
        String sql = "SELECT * FROM CELESTIAL_BODIES WHERE type = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, type.ordinal());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    celestialBodyConsumer.accept(mapToCelestialBody(resultSet));
                }
            }
        }
    }

    private CelestialBody mapToCelestialBody(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("celestial_body_id");
        String name = resultSet.getString("name");
        int typeId = resultSet.getInt("type");
        String description = resultSet.getString("description");

        CelestialType type = CelestialType.values()[typeId - 1]; // Adjusting for 0-based index

        return new CelestialBody(id, name, type, description);
    }
}
