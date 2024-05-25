package com.db_course.dao;

import com.db_course.entity_model.CelestialPath;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public List<CelestialPath> getPathsByBodyId(int bodyId) throws SQLException {
        List<CelestialPath> paths = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE + " WHERE body_a_id = ? OR body_b_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bodyId);
            statement.setInt(2, bodyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    paths.add(mapToCelestialPath(resultSet));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return paths;

    }

    private CelestialPath mapToCelestialPath(ResultSet resultSet) throws SQLException {
        int pathwayId = resultSet.getInt("pathway_id");
        int bodyAId = resultSet.getInt("body_a_id");
        int bodyBId = resultSet.getInt("body_b_id");
        BigDecimal distanceKm = resultSet.getBigDecimal("distance_km");
        return new CelestialPath(pathwayId, bodyAId, bodyBId, distanceKm);
    }
}
