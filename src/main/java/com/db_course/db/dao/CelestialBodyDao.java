package com.db_course.db.dao;

import com.db_course.db.entity_model.CelestialBody;
import com.db_course.db.entity_model.CelestialType;

import java.math.BigDecimal;
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
        String sql = "SELECT * FROM CELESTIAL_BODIES WHERE type_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, type.ordinal() + 1); // Adjusting for 1-based index in the database
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
        int typeId = resultSet.getInt("type_id");
        String description = resultSet.getString("description");
        BigDecimal surfacePressure = resultSet.getBigDecimal("surface_pressure");
        BigDecimal surfaceTemperatureMin = resultSet.getBigDecimal("surface_temperature_min");
        BigDecimal surfaceTemperatureMax = resultSet.getBigDecimal("surface_temperature_max");
        BigDecimal coreTemperature = resultSet.getBigDecimal("core_temperature");
        boolean hasBeenExplored = resultSet.getBoolean("has_been_explored");
        String radiationLevels = resultSet.getString("radiation_levels");
        boolean hasWater = resultSet.getBoolean("has_water");
        BigDecimal surfaceArea = resultSet.getBigDecimal("surface_area");
        boolean isSurfaceHard = resultSet.getBoolean("is_surface_hard");
        BigDecimal mass = resultSet.getBigDecimal("mass");
        BigDecimal gravitationalFieldHeight = resultSet.getBigDecimal("gravitational_field_height");
        Integer rotatesAroundId = resultSet.getInt("rotates_around_id");


  /*      if (resultSet.wasNull()) {
            rotatesAroundId = null; // Handle null values
        }*/
        BigDecimal movingSpeed = resultSet.getBigDecimal("moving_speed");
        BigDecimal rotationSpeed = resultSet.getBigDecimal("rotation_speed");

        CelestialType type = CelestialType.values()[typeId - 1]; // Adjusting for 0-based index
        return new CelestialBody(
                id,
                name,
                type,
                description,
                surfacePressure,
                surfaceTemperatureMin,
                surfaceTemperatureMax,
                coreTemperature,
                hasBeenExplored,
                radiationLevels,
                hasWater,
                surfaceArea,
                isSurfaceHard,
                mass,
                gravitationalFieldHeight,
                rotatesAroundId,
                movingSpeed,
                rotationSpeed);
    }
}
