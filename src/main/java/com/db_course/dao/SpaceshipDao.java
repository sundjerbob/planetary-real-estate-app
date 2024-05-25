package com.db_course.dao;

import com.db_course.entity_model.Spaceship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpaceshipDao {

    private Connection connection;

    public SpaceshipDao(Connection connection) {
        this.connection = connection;
    }

    public void addSpaceship(Spaceship spaceship) throws SQLException {
        String sql = "INSERT INTO SPACESHIPS (name, model, passenger_capacity, fuel_capacity, max_travel_range, traveling_speed, manufacturer, launch_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            mapToSpaceship(spaceship, statement);
            statement.executeUpdate();
        }
    }

    public Spaceship getSpaceship(int spaceshipId) throws SQLException {
        String sql = "SELECT * FROM SPACESHIPS WHERE spaceship_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, spaceshipId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Spaceship(
                            resultSet.getInt("spaceship_id"),
                            resultSet.getString("name"),
                            resultSet.getString("model"),
                            resultSet.getInt("passenger_capacity"),
                            resultSet.getBigDecimal("fuel_capacity"),
                            resultSet.getBigDecimal("max_travel_range"),
                            resultSet.getBigDecimal("traveling_speed"),
                            resultSet.getString("manufacturer")
                    );
                }
            }
        }
        return null;
    }

    public List<Spaceship> getAllSpaceships() throws SQLException {
        List<Spaceship> spaceships = new ArrayList<>();
        String sql = "SELECT * FROM SPACESHIPS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                spaceships.add(new Spaceship(
                        resultSet.getInt("spaceship_id"),
                        resultSet.getString("name"),
                        resultSet.getString("model"),
                        resultSet.getInt("passenger_capacity"),
                        resultSet.getBigDecimal("fuel_capacity"),
                        resultSet.getBigDecimal("max_travel_range"),
                        resultSet.getBigDecimal("traveling_speed"),
                        resultSet.getString("manufacturer")
                ));
            }
        }
        return spaceships;
    }

    public void updateSpaceship(Spaceship spaceship) throws SQLException {
        String sql = "UPDATE SPACESHIPS SET name = ?, model = ?, passenger_capacity = ?, fuel_capacity = ?, max_travel_range = ?, traveling_speed = ?, manufacturer = ?, launch_date = ? WHERE spaceship_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            mapToSpaceship(spaceship, statement);
            statement.setInt(9, spaceship.getSpaceshipId());
            statement.executeUpdate();
        }
    }

    private void mapToSpaceship(Spaceship spaceship, PreparedStatement statement) throws SQLException {
        statement.setString(1, spaceship.getName());
        statement.setString(2, spaceship.getModel());
        statement.setInt(3, spaceship.getPassengerCapacity());
        statement.setBigDecimal(4, spaceship.getFuelCapacity());
        statement.setBigDecimal(5, spaceship.getMaxTravelRange());
        statement.setBigDecimal(6, spaceship.getTravelingSpeed());
        statement.setString(7, spaceship.getManufacturer());
    }

    public void deleteSpaceship(int spaceshipId) throws SQLException {
        String sql = "DELETE FROM SPACESHIPS WHERE spaceship_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, spaceshipId);
            statement.executeUpdate();
        }
    }
}
