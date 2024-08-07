package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.CelestialType;
import com.db_course.be.filter.entity_filters.impl.CelestialTypeFilter;

import java.sql.*;
import java.util.function.Consumer;

public class CelestialTypeDao {


    private static final String TABLE = "CELESTIAL_TYPES";
    private final Connection connection;


    public CelestialTypeDao() {
        this.connection = DB_Client.getInstance().getConnection();
    }


    public CelestialType getCelestialTypeById(int id) {
        String query = "SELECT * FROM " + TABLE + " WHERE celestial_type_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? mapToCelestialType(resultSet) : null;

            }
        } catch (SQLException e) {
            throw new RuntimeException("CelestialTypeDao.getCelestialTypeById() says: " + e.getMessage());
        }


    }

    public void processAllCelestialTypes(Consumer<CelestialType> consumer) {
        String query = "SELECT * FROM " + TABLE;

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToCelestialType(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("CelestialTypeDao.processAllCelestialTypes() says: " + e.getMessage());
        }

    }


    public void processFilteredCelestialTypes(Consumer<CelestialType> consumer, CelestialTypeFilter filter) {

        try (
                PreparedStatement statement = filter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToCelestialType(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("CelestialTypeDao.processFilteredCelestialTypes() says: " + e.getMessage());
        }

    }

    private CelestialType mapToCelestialType(ResultSet resultSet) throws SQLException {
        return new CelestialType(
                resultSet.getInt("celestial_type_id"),
                resultSet.getString("name"),
                resultSet.getString("description")
        );


    }
}
