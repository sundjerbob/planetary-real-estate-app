package com.db_course.dao;

import com.db_course.entity_model.SpaceshipRoom;

import java.sql.*;
import java.util.function.Consumer;

public class SpaceshipRoomDao {

    private final Connection connection;
    private static final String TABLE = "SPACESHIP_ROOMS";

    public SpaceshipRoomDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(SpaceshipRoom room) {
        String sql = "INSERT INTO " + TABLE + " (room_id, spaceship_id, room_number, perks, num_hibernation_capsules) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, room.getRoomId());
            statement.setInt(2, room.getSpaceshipId());
            statement.setInt(3, room.getRoomNumber());
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
            statement.setInt(2, room.getRoomNumber());
            statement.setString(3, room.getPerks());
            statement.setInt(4, room.getNumHibernationCapsules());
            statement.setInt(5, room.getRoomId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SpaceshipRoomDao.update() says: " + e.getMessage());
        }
    }

    public SpaceshipRoom searchById(int roomId) {
        String sql = "SELECT * FROM " + TABLE + " WHERE room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToSpaceshipRoom(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("SpaceshipRoomDao.searchById() says: " + e.getMessage());
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
        int roomNumber = resultSet.getInt("room_number");
        String perks = resultSet.getString("perks");
        int numHibernationCapsules = resultSet.getInt("num_hibernation_capsules");
        return new SpaceshipRoom(roomId, spaceshipId, roomNumber, perks, numHibernationCapsules);
    }
}
