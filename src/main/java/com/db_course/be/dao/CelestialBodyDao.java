package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.CelestialBody;
import com.db_course.be.entity_model.RadiationLevel;
import com.db_course.be.filter.entity_filters.impl.CelestialBodyFilter;

import java.math.BigDecimal;
import java.sql.*;
import java.util.function.Consumer;


public class CelestialBodyDao {


    private final Connection connection;
    private static final String TABLE = "CELESTIAL_BODIES";


    public CelestialBodyDao() {

        this.connection = DB_Client.getInstance().getConnection();
    }


    /******************************************************************************************************************/
    public void processAllCelestialBodies(Consumer<CelestialBody> celestialBodyConsumer) {
        String sql = "SELECT * FROM " + TABLE;

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                celestialBodyConsumer.accept(mapToCelestialBody(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException("CelestialBodyDao.processAllCelestialBodies() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public void processFilteredCelestialBodies(Consumer<CelestialBody> celestialBodyConsumer, CelestialBodyFilter filter) {

        try (
                PreparedStatement statement = filter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                celestialBodyConsumer.accept(mapToCelestialBody(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException("CelestialBodyDao.processFilteredCelestialBodies() says: " + e.getMessage());
        }

    }


    /******************************************************************************************************************/
    public void processCelestialBodiesByTypeId(int typeId, Consumer<CelestialBody> celestialBodyConsumer) throws RuntimeException {
        String sql = "SELECT * FROM " + TABLE + " WHERE type_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, typeId);
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    celestialBodyConsumer.accept(mapToCelestialBody(resultSet));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("CelestialBodyDao.processCelestialBodiesByType() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public CelestialBody getCelestialBodyById(int id) throws RuntimeException {
        String sql = "SELECT * FROM " + TABLE + " WHERE celestial_body_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? mapToCelestialBody(resultSet) : null; // Handle case where no record is found
            }
        } catch (Exception e) {
            throw new RuntimeException("CelestialBodyDao.getCelestialBodyById() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
//    public void processDynamicFilters(CelestialBodyFilter filter) {
//        String query = filter.generateQuery();
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(query);
//        } catch (Exception e) {
//            throw new RuntimeException("CelestialBodyDao.processDynamicFilters() says: " + e.getMessage());
//        }
//
//    }

    /**TODO : IMPL THIS sht /
     * asdad
     */
    /******************************************************************************************************************/
    private CelestialBody mapToCelestialBody(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("celestial_body_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        BigDecimal surfacePressure = resultSet.getBigDecimal("surface_pressure");
        BigDecimal surfaceTemperatureMin = resultSet.getBigDecimal("surface_temperature_min");
        BigDecimal surfaceTemperatureMax = resultSet.getBigDecimal("surface_temperature_max");
        BigDecimal coreTemperature = resultSet.getBigDecimal("core_temperature");
        boolean explored = resultSet.getBoolean("explored");
        String radiationLevel = resultSet.getString("radiation_level");
        boolean hasWater = resultSet.getBoolean("has_water");
        BigDecimal surfaceArea = resultSet.getBigDecimal("surface_area");
        BigDecimal mass = resultSet.getBigDecimal("mass");
        BigDecimal gravitationalFieldHeight = resultSet.getBigDecimal("gravitation_field_height");
        BigDecimal movingSpeed = resultSet.getBigDecimal("moving_speed");
        BigDecimal rotationSpeed = resultSet.getBigDecimal("rotation_speed");

        int typeId = resultSet.getInt("type_id");
        Integer rotatesAroundId = resultSet.getInt("rotates_around_id");

        if (resultSet.wasNull()) {
            rotatesAroundId = null;
        }

        return new CelestialBody(
                id,
                rotatesAroundId,
                name,
                typeId,
                description,
                surfacePressure,
                surfaceTemperatureMin,
                surfaceTemperatureMax,
                coreTemperature,
                explored,
                RadiationLevel.parse(radiationLevel),
                hasWater,
                surfaceArea,
                mass,
                gravitationalFieldHeight,
                movingSpeed,
                rotationSpeed);
    }


}
