package com.db_course.dao;

import com.db_course.entity_model.CelestialPath;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class CelestialPathDao {

    private final Connection connection;
    private static final String TABLE = "CELESTIAL_PATHS";

    public CelestialPathDao(Connection connection) {
        this.connection = connection;
    }

    public void processAllPaths(Consumer<CelestialPath> pathConsumer) {
        String sql = "SELECT * FROM " + TABLE;

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                pathConsumer.accept(mapToCelestialPath(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void processPathsByBodyId(int bodyId, Consumer<CelestialPath> pathConsumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE body_a_id = ? OR body_b_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bodyId);
            statement.setInt(2, bodyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pathConsumer.accept(mapToCelestialPath(resultSet));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CelestialPath getPathByBodyIds(int bodyAId, int bodyBId) {
        String sql = "SELECT * FROM " + TABLE + " WHERE body_a_id = ? AND body_b_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bodyAId);
            statement.setInt(2, bodyBId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToCelestialPath(resultSet);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean addCelestialPath(CelestialPath celestialPath) {
        String sql = "INSERT INTO " + TABLE + " (body_a_id, body_b_id, distance_km) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, celestialPath.getBodyA_Id());
            statement.setInt(2, celestialPath.getBodyB_Id());
            statement.setBigDecimal(3, celestialPath.getDistanceKm());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateCelestialPath(CelestialPath celestialPath) {
        String sql = "UPDATE " + TABLE + " SET body_a_id = ?, body_b_id = ?, distance_km = ? WHERE pathway_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, celestialPath.getBodyA_Id());
            statement.setInt(2, celestialPath.getBodyB_Id());
            statement.setBigDecimal(3, celestialPath.getDistanceKm());
            statement.setInt(4, celestialPath.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCelestialPath(int pathId) {
        String sql = "DELETE FROM " + TABLE + " WHERE pathway_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pathId);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CelestialPath mapToCelestialPath(ResultSet resultSet) throws SQLException {
        int pathwayId = resultSet.getInt("pathway_id");
        int bodyAId = resultSet.getInt("body_a_id");
        int bodyBId = resultSet.getInt("body_b_id");
        BigDecimal distanceKm = resultSet.getBigDecimal("distance_km");
        return new CelestialPath(pathwayId, bodyAId, bodyBId, distanceKm);
    }
}