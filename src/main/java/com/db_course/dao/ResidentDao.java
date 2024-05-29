package com.db_course.dao;

import com.db_course.db_config.DB_Client;
import com.db_course.entity_model.Resident;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.Consumer;

public class ResidentDao {


    private final Connection connection;
    private final static String TABLE = "RESIDENTS";


    public ResidentDao(Connection connection) {
        this.connection = DB_Client.getInstance().getConnection();
    }


    public void processAllResident(Consumer<Resident> consumer) {

        String sql = "SELECT * FROM " + TABLE;

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                consumer.accept(mapToResident(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException("ResidentDao.processAllResident() says: " + e.getMessage());
        }
    }


    public void processResidentsWhoDiedBetweenAge20And40(int celestialBodyId, Consumer<Resident> consumer) {

        String sql = "SELECT r.resident_id, r.full_name, r.gender, r.birth_date, r.death_date " +
                "FROM RESIDENTS r " +
                "JOIN CELESTIAL_BODY_RESIDENTS cbr ON r.resident_id = cbr.resident_id " +
                "WHERE cbr.celestial_body_id = ? " +
                "AND cbr.resident_until IS NULL " +
                "AND r.death_date IS NOT NULL " +
                "AND TIMESTAMPDIFF(YEAR, r.birth_date, r.death_date) BETWEEN 20 AND 40 " +
                "AND TIMESTAMPDIFF(YEAR, cbr.resident_from, IFNULL(cbr.resident_until, NOW())) > 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, celestialBodyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    consumer.accept(mapToResident(resultSet));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("ResidentDao.processResidentsWhoDiedBetweenAge20And40() says: " + e.getMessage());
        }

    }


    private Resident mapToResident(ResultSet rs) throws SQLException {

        int residentId = rs.getInt("resident_id");
        String fullName = rs.getString("full_name");
        String gender = rs.getString("gender");
        LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
        LocalDate deathDate = rs.getDate("death_date") == null ? null : rs.getDate("death_date").toLocalDate();
        return new Resident(residentId, fullName, gender, birthDate, deathDate);

    }


}
