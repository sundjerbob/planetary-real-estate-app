package com.db_course.dao;

import com.db_course.db_config.DB_Client;
import com.db_course.entity_model.CelestialType;

import java.sql.*;
import java.util.function.Consumer;

public class CelestialTypeDao {


    private static final String TABLE = "CELESTIAL_TYPES";
    private final Connection connection;


    public CelestialTypeDao() {
        this.connection = DB_Client.getInstance().getConnection();
    }


    public CelestialType getCelestialTypeById(int id) {
        String query = "SELECT * FROM " + TABLE + " WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet.next() ? mapToCelestialType(resultSet) : null;
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


    private CelestialType mapToCelestialType(ResultSet resultSet) throws SQLException {
        return new CelestialType(
                resultSet.getInt("celestial_type_id"),
                resultSet.getString("name"),
                resultSet.getString("description")
        );


    }
}
