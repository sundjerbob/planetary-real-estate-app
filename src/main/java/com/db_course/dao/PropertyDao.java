package com.db_course.dao;

import com.db_course.entity_model.Property;

import java.math.BigDecimal;
import java.sql.*;
import java.util.function.Consumer;

public class PropertyDao {


    private final Connection connection;
    private static final String TABLE = "PROPERTIES";


    public PropertyDao(Connection connection) {
        this.connection = connection;
    }


    /******************************************************************************************************************/
    public void insert(Property property) {
        String sql = "INSERT INTO " + TABLE + " (property_id, celestial_body_id, sold_to_user_id, global_registry_nb, address, square_meters, description, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, property.getPropertyId());
            statement.setInt(2, property.getCelestialBodyId());
            statement.setObject(3, property.getSoldToUserId(), Types.INTEGER);
            statement.setInt(4, property.getGlobalRegistryNb());
            statement.setString(5, property.getAddress());
            statement.setBigDecimal(6, property.getSquareMeters());
            statement.setString(7, property.getDescription());
            statement.setBigDecimal(8, property.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("PropertyDao.insert() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public void update(Property property) {
        String sql = "UPDATE " + TABLE + " SET celestial_body_id = ?, sold_to_user_id = ?, global_registry_nb = ?, address = ?, square_meters = ?, description = ?, price = ? WHERE property_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, property.getCelestialBodyId());
            statement.setObject(2, property.getSoldToUserId(), Types.INTEGER);
            statement.setInt(3, property.getGlobalRegistryNb());
            statement.setString(4, property.getAddress());
            statement.setBigDecimal(5, property.getSquareMeters());
            statement.setString(6, property.getDescription());
            statement.setBigDecimal(7, property.getPrice());
            statement.setInt(8, property.getPropertyId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("PropertyDao.update() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public Property searchById(int propertyId) {
        String sql = "SELECT * FROM " + TABLE + " WHERE property_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, propertyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToProperty(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("PropertyDao.searchById() says: " + e.getMessage());
        }
        return null; // Return null if no property is found
    }


    /******************************************************************************************************************/
    public void processPropertiesByCelestialBodyId(int celestialBodyId, Consumer<Property> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE celestial_body_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, celestialBodyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    consumer.accept(mapToProperty(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("PropertyDao.processPropertiesByCelestialBodyId() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    private Property mapToProperty(ResultSet resultSet) throws SQLException {
        int propertyId = resultSet.getInt("property_id");
        int celestialBodyId = resultSet.getInt("celestial_body_id");
        Integer soldToUserId = (Integer) resultSet.getObject("sold_to_user_id");
        int globalRegistryNb = resultSet.getInt("global_registry_nb");
        String address = resultSet.getString("address");
        BigDecimal squareMeters = resultSet.getBigDecimal("square_meters");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        return new Property(propertyId, celestialBodyId, soldToUserId, globalRegistryNb, address, squareMeters, description, price);
    }
}
