package com.db_course.dao;


import com.db_course.entity_model.CelestialBodyResident;

import java.sql.*;
import java.time.LocalDate;
import java.util.function.Consumer;


public class CelestialBodyResidentDao {


    private final Connection connection;
    private static final String TABLE = "CELESTIAL_BODY_RESIDENTS";


    public CelestialBodyResidentDao(Connection connection) {
        this.connection = connection;
    }


    /******************************************************************************************************************/
    public void insertCelestialBodyResident(CelestialBodyResident resident) {

        String query = "INSERT INTO celestial_body_resident (resident_id, celestial_body_id, resident_from, resident_until) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, resident.getResidentId());
            statement.setInt(2, resident.getCelestialBodyId());
            statement.setDate(3, Date.valueOf(resident.getResidentFrom()));
            statement.setDate(3, Date.valueOf(resident.getResidentUntil()));
            statement.executeUpdate();
        } catch (Exception e) {

            throw new RuntimeException("CelestialBodyResidentDao.insertCelestialBodyResident() says: " + e.getMessage());
        }

    }


    /******************************************************************************************************************/
    public CelestialBodyResident getByResidentAndCelestialBodyIds(int residentId, int celestialBodyId) {

        String query = "SELECT * FROM " + TABLE + " WHERE resident_id = ? AND celestial_body_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, residentId);
            statement.setInt(2, celestialBodyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return mapToCelestialBodyResident(resultSet);
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("CelestialBodyResidentDao.getByResidentAndCelestialBodyIds() says: " + e.getMessage());

        }

    }


    /******************************************************************************************************************/
    void processCelestialBodyResidentsByCelestialBodyId(int celestialBodyId, Consumer<CelestialBodyResident> consumer) {

        String query = "SELECT * FROM " + TABLE + " WHERE celestial_body = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, celestialBodyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    consumer.accept(mapToCelestialBodyResident(resultSet));
            }
        } catch (Exception e) {

            throw new RuntimeException("CelestialBodyResidentDao.processCelestialBodyResidentsByCelestialBodyId() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public void processAllCelestialBodyResidents(Consumer<CelestialBodyResident> consumer) {
        String query = "SELECT * FROM " + TABLE;

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                consumer.accept(mapToCelestialBodyResident(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException("CelestialBodyResidentDao.processAllCelestialBodyResidents() says: " + e.getMessage());

        }
    }


    /******************************************************************************************************************/
    public void processCelestialBodyResidentsByResidentId(int residentId, Consumer<CelestialBodyResident> consumer) {

    String statement = "SELECT * FROM " + TABLE + " WHERE resident_id = ?";


    }


    /******************************************************************************************************************/
    private CelestialBodyResident mapToCelestialBodyResident(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("celestial_body_resident_id");
        int residentId = resultSet.getInt("resident_id");
        int celestialBodyId = resultSet.getInt("celestial_body_id");
        LocalDate residentFrom = resultSet.getObject("resident_from", LocalDate.class);
        LocalDate residentUntil = resultSet.getObject("resident_until", LocalDate.class);
        return new CelestialBodyResident(id, celestialBodyId, residentId, residentFrom, residentUntil);
    }


}
