package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.Spaceship;
import com.db_course.be.filter.entity_filters.impl.SpaceshipFilter;

import java.sql.*;
import java.util.function.Consumer;

public class SpaceshipDao {

    private final Connection connection;
    private final static String TABLE = "SPACESHIPS";

    public SpaceshipDao() {

        this.connection = DB_Client.getInstance().getConnection();

    }


    /********************************************************************************************/
//    public void addSpaceship(Spaceship spaceship) throws SQLException {
//        String sql = "INSERT INTO " + TABLE + " (name, model, passenger_capacity, fuel_capacity, max_travel_range, traveling_speed, manufacturer, launch_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            mapSpaceshipToStatement(spaceship, statement);
//            statement.executeUpdate();
//        }
//    }


    /******************************************************************************************************************/
    public Spaceship getSpaceshipById(int spaceshipId) {
        String sql = "SELECT * FROM " + TABLE + " WHERE spaceship_id = ?";
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
        } catch (Exception e) {
            throw new RuntimeException("SpaceshipDao.getSpaceshipById() says: " + e.getMessage());
        }
        return null;
    }


    /******************************************************************************************************************/
    public void processAllSpaceships(Consumer<Spaceship> consumer) {
        String sql = "SELECT * FROM " + TABLE;
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToSpaceship(resultSet));
            }

        } catch (Exception e) {
            throw new RuntimeException("SpaceshipDao.getAllSpaceships() says: " + e.getMessage());
        }
    }

    /******************************************************************************************************************/
    public void processFilteredSpaceships(Consumer<Spaceship> consumer, SpaceshipFilter filter) {

        try (
                PreparedStatement statement = filter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToSpaceship(resultSet));
            }

        } catch (Exception e) {
            throw new RuntimeException("SpaceshipDao.getAllSpaceships() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
//    public void updateSpaceship(Spaceship spaceship) throws SQLException {
//        String sql = "UPDATE " + TABLE + " SET name = ?, model = ?, passenger_capacity = ?, fuel_capacity = ?, max_travel_range = ?, traveling_speed = ?, manufacturer = ?, launch_date = ? WHERE spaceship_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            mapSpaceshipToStatement(spaceship, statement);
//            statement.setInt(9, spaceship.getId());
//            statement.executeUpdate();
//        } catch (Exception e) {
//            throw new RuntimeException("SpaceshipDao.updateSpaceship() says: " + e.getMessage());
//        }
//    }
//
//
//    /******************************************************************************************************************/
//    public void deleteSpaceship(int spaceshipId) throws SQLException {
//        String sql = "DELETE FROM " + TABLE + " WHERE spaceship_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, spaceshipId);
//            statement.executeUpdate();
//        } catch (Exception e) {
//            throw new RuntimeException("SpaceshipDao.deleteSpaceship() says: " + e.getMessage());
//        }
//    }
//

    /******************************************************************************************************************/
    private Spaceship mapToSpaceship(ResultSet resultSet) throws SQLException {
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


    /******************************************************************************************************************/
    private void mapSpaceshipToStatement(Spaceship spaceship, PreparedStatement statement) throws SQLException {
        statement.setString(1, spaceship.getName());
        statement.setString(2, spaceship.getModel());
        statement.setInt(3, spaceship.getPassengerCapacity());
        statement.setBigDecimal(4, spaceship.getFuelCapacity());
        statement.setBigDecimal(5, spaceship.getMaxTravelRange());
        statement.setBigDecimal(6, spaceship.getTravelingSpeed());
        statement.setString(7, spaceship.getManufacturer());
    }

}
