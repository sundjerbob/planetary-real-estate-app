package com.db_course.dao;

import com.db_course.entity_model.Atmosphere;

import java.math.BigDecimal;
import java.sql.*;
import java.util.function.Consumer;

public class AtmosphereDao {

    private final Connection connection;
    private static final String TABLE = "ATMOSPHERES";

    public AtmosphereDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Atmosphere atmosphere) {
        String sql = "INSERT INTO " + TABLE + " (id, celestial_body_id, max_temperature, min_temperature, atmosphere_height, ampere_pressure) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, atmosphere.getId());
            statement.setInt(2, atmosphere.getCelestialBodyId());
            statement.setBigDecimal(3, atmosphere.getMaxTemperature());
            statement.setBigDecimal(4, atmosphere.getMinTemperature());
            statement.setBigDecimal(5, atmosphere.getAtmosphereHeight());
            statement.setBigDecimal(6, atmosphere.getAmperePressure());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("AtmosphereDao.insert() says: " + e.getMessage());
        }
    }

    public void update(Atmosphere atmosphere) {
        String sql = "UPDATE " + TABLE + " SET celestial_body_id = ?, max_temperature = ?, min_temperature = ?, atmosphere_height = ?, ampere_pressure = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, atmosphere.getCelestialBodyId());
            statement.setBigDecimal(2, atmosphere.getMaxTemperature());
            statement.setBigDecimal(3, atmosphere.getMinTemperature());
            statement.setBigDecimal(4, atmosphere.getAtmosphereHeight());
            statement.setBigDecimal(5, atmosphere.getAmperePressure());
            statement.setInt(6, atmosphere.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("AtmosphereDao.update() says: " + e.getMessage());
        }
    }

    public Atmosphere searchById(int id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToAtmosphere(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("AtmosphereDao.searchById() says: " + e.getMessage());
        }
        return null; // Return null if no atmosphere is found
    }

    public void processAtmospheresByCelestialBodyId(int celestialBodyId, Consumer<Atmosphere> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE celestial_body_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, celestialBodyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    consumer.accept(mapToAtmosphere(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("AtmosphereDao.processAtmospheresByCelestialBodyId() says: " + e.getMessage());
        }
    }

    private Atmosphere mapToAtmosphere(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int celestialBodyId = resultSet.getInt("celestial_body_id");
        BigDecimal maxTemperature = resultSet.getBigDecimal("max_temperature");
        BigDecimal minTemperature = resultSet.getBigDecimal("min_temperature");
        BigDecimal atmosphereHeight = resultSet.getBigDecimal("atmosphere_height");
        BigDecimal amperePressure = resultSet.getBigDecimal("ampere_pressure");
        return new Atmosphere(id, celestialBodyId, maxTemperature, minTemperature, atmosphereHeight, amperePressure);
    }
}
