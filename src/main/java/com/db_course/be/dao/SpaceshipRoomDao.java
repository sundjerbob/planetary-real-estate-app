package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.SpaceshipRoom;
import com.db_course.be.filter.entity_filters.impl.SpaceShipRoomFilter;

import java.sql.*;
import java.util.function.Consumer;

public class SpaceshipRoomDao {

    private final Connection connection;
    private static final String TABLE = "SPACESHIP_ROOMS";

    public SpaceshipRoomDao() {
        this.connection = DB_Client.getInstance().getConnection();
    }


    public void processAllSpaceshipRooms(Consumer<SpaceshipRoom> consumer) {
        String sql = "SELECT * FROM " + TABLE;
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToSpaceshipRoom(resultSet));
            }
        } catch (SQLException exception) {
            throw new RuntimeException("SpaceshipRoomDao.processAllSpaceshipRooms() says: " + exception.getMessage());
        }

    }


    public void processFilteredSpaceshipRooms(Consumer<SpaceshipRoom> consumer, SpaceShipRoomFilter filter) {
        try (
                PreparedStatement statement = filter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToSpaceshipRoom(resultSet));
            }
        } catch (SQLException exception) {
            throw new RuntimeException("SpaceshipRoomDao.processAllSpaceshipRooms() says: " + TABLE);
        }

    }

    public void insert(SpaceshipRoom room) {
        String sql = "INSERT INTO " + TABLE + " (room_id, spaceship_id, room_number, perks, num_hibernation_capsules) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, room.getRoomId());
            statement.setInt(2, room.getSpaceshipId());
            statement.setString(3, room.getRoomNumber());
            statement.setString(4, room.getPerks());
            statement.setInt(5, room.getNumHibernationCapsules());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SpaceshipRoomDao.insert() says: " + e.getMessage());
        }
    }

    public void update(SpaceshipRoom room) {
        String sql = "UPDATE " + TABLE + " SET spaceship_id = ?, room_number = ?, perks = ?, num_hibernation_capsules = ? WHERE room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, room.getSpaceshipId());
            statement.setString(2, room.getRoomNumber());
            statement.setString(3, room.getPerks());
            statement.setInt(4, room.getNumHibernationCapsules());
            statement.setInt(5, room.getRoomId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SpaceshipRoomDao.update() says: " + e.getMessage());
        }
    }

    public SpaceshipRoom getRoomById(int roomId) {
        String sql = "SELECT * FROM " + TABLE + " WHERE room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToSpaceshipRoom(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("SpaceshipRoomDao.getAtmosphereById() says: " + e.getMessage());
        }
        return null; // Return null if no room is found
    }

    public void searchBySpaceshipId(int spaceshipId, Consumer<SpaceshipRoom> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE spaceship_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, spaceshipId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    consumer.accept(mapToSpaceshipRoom(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("SpaceshipRoomDao.searchBySpaceshipId() says: " + e.getMessage());
        }
    }

    private SpaceshipRoom mapToSpaceshipRoom(ResultSet resultSet) throws SQLException {
        int roomId = resultSet.getInt("room_id");
        int spaceshipId = resultSet.getInt("spaceship_id");
        String roomNumber = resultSet.getString("room_number");
        String perks = resultSet.getString("perks");
        int numHibernationCapsules = resultSet.getInt("hibernation_capsules_nb");
        return new SpaceshipRoom(roomId, spaceshipId, roomNumber, perks, numHibernationCapsules);
    }
}
