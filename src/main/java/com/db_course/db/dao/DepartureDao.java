package com.db_course.db.dao;

import com.db_course.db.entity_model.Departure;

import java.sql.*;
import java.util.function.Consumer;

public class DepartureDao {

    private final Connection connection;
    private static final String table = "DEPARTURES";

    public DepartureDao(Connection connection) {
        this.connection = connection;
    }

    public void processAllDepartures(Consumer<Departure> departureConsumer) throws SQLException {

        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT departure_id, departure_date, passenger_id FROM " + table
                );
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                int departureId = resultSet.getInt("departure_id");
                Timestamp departureDate = resultSet.getTimestamp("departure_date");
                int passengerId = resultSet.getInt("passenger_id");

                Departure departure = new Departure(departureId, departureDate.toLocalDateTime(), passengerId);

                departureConsumer.accept(departure);
            }
        }
    }

    public Departure insertDeparture(Departure departure) throws SQLException {
        String sql = "INSERT INTO " + table + " (departure_date, passenger_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(departure.getDepartureDate()));
            statement.setInt(2, departure.getPassengerId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating departure failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    departure.setDepartureId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating departure failed, no ID obtained.");
                }
            }
        }

        return departure;
    }
}
