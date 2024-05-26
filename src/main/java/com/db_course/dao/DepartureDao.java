package com.db_course.dao;

import com.db_course.entity_model.Departure;

import java.sql.*;
import java.util.function.Consumer;

public class DepartureDao {

    private final Connection connection;
    private static final String table = "DEPARTURES";

    public DepartureDao(Connection connection) {
        this.connection = connection;
    }


    public void processAllDepartures(Consumer<Departure> departureConsumer) {

        try (
                PreparedStatement statement = connection.prepareStatement("SELECT departure_id, departure_date, passenger_id FROM " + table);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                int departureId = resultSet.getInt("departure_id");
                Timestamp departureDate = resultSet.getTimestamp("departure_date");
                int originBodyId = resultSet.getInt("celestial_origin_id");
                int destinationBodyId = resultSet.getInt("celestial_destination_id");

                Departure departure = new Departure(
                        departureId,
                        departureDate.toLocalDateTime(),
                        originBodyId,
                        destinationBodyId
                );

                departureConsumer.accept(departure);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DepartureDao.processAllDepartures() says: " + e.getMessage());
        }
    }


    public Departure insertDeparture(Departure departure) {
        String sql = "INSERT INTO " + table + " (departure_date, passenger_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(departure.getDepartureDate()));
            statement.setInt(2, departure.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating departure failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    departure.setId(generatedKeys.getInt(1));
                } else {
                    throw new RuntimeException("Creating departure failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DepartureDao.insertDeparture() says: " + e.getMessage());
        }

        return departure;
    }

}
