package com.db_course.dao;

import com.db_course.entity_model.Mission;

import java.sql.*;
import java.time.LocalDate;
import java.util.function.Consumer;

public class MissionDao {

    private static final String TABLE = "MISSIONS";
    private final Connection connection;

    public MissionDao(Connection connection) {
        this.connection = connection;
    }

    public void processAllMissions(Consumer<Mission> consumer) {
        String sql = "SELECT * FROM " + TABLE + " ORDER BY start_date DESC";
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToMission(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("MissionDao.processAllMissions() says: " + e.getMessage());
        }
    }

    public void processMissionsByExploredBodyId(int exploredBodyId, Consumer<Mission> mission) {
        String sql = "SELECT * FROM " + TABLE + " WHERE explored_body_id = ? ORDER BY start_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, exploredBodyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    mission.accept(mapToMission(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("MissionDao.processMissionsByExploredBodyId() says: " + e.getMessage());
        }
    }

    public void processMissionsByStartDate(LocalDate startDate, Consumer<Mission> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE start_date = ? ORDER BY start_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, startDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    consumer.accept(mapToMission(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("MissionDao.processMissionsByStartDate() says: " + e.getMessage());
        }
    }

    public void processMissionsByStartDateBetween(LocalDate startDate, LocalDate endDate, Consumer<Mission> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE start_date BETWEEN ? AND ? ORDER BY start_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, startDate);
            statement.setObject(2, endDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    consumer.accept(mapToMission(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("MissionDao.processMissionsByStartDateBetween() says: " + e.getMessage());
        }
    }

    public void processMissionsByEndDate(LocalDate endDate, Consumer<Mission> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE end_date = ? ORDER BY start_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, endDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    consumer.accept(mapToMission(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("MissionDao.processMissionsByEndDate() says: " + e.getMessage());
        }
    }

    public void processMissionsByStatus(boolean completed, Consumer<Mission> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE completed = ? ORDER BY start_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, completed);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    consumer.accept(mapToMission(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("MissionDao.processMissionsByStatus() says: " + e.getMessage());
        }
    }

    private Mission mapToMission(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            int celestialBodyId = resultSet.getInt("explored_body_id");
            int spaceshipId = resultSet.getInt("spaceship_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            LocalDate startDate = resultSet.getObject("start_date", LocalDate.class);
            LocalDate endDate = resultSet.getObject("end_date", LocalDate.class);
            boolean completed = resultSet.getBoolean("completed");
            return new Mission(id, celestialBodyId, spaceshipId, name, description, startDate, endDate, completed);
        } catch (SQLException e) {
            throw new RuntimeException("MissionDao.mapToMission() says: " + e.getMessage());
        }
    }
}
