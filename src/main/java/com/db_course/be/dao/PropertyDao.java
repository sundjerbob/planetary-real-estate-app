package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.Property;
import com.db_course.be.filter.entity_filters.impl.PropertyFilter;

import java.math.BigDecimal;
import java.sql.*;
import java.util.function.Consumer;

public class PropertyDao {


    private final Connection connection;
    private static final String TABLE = "PROPERTIES";


    public PropertyDao() {
        this.connection = DB_Client.getInstance().getConnection();

    }


    /******************************************************************************************************************/
    public void insert(Property property) {
        String sql = "INSERT INTO " + TABLE + " (property_id, celestial_body_id, sold_to_user_id, global_registry_nb, address, square_meters, description, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, property.getId());
            statement.setInt(2, property.getCelestialBodyId());
            statement.setObject(3, property.getSoldToUserId(), Types.INTEGER);
            statement.setInt(4, property.getPropertyRegNb());
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
            statement.setInt(3, property.getPropertyRegNb());
            statement.setString(4, property.getAddress());
            statement.setBigDecimal(5, property.getSquareMeters());
            statement.setString(6, property.getDescription());
            statement.setBigDecimal(7, property.getPrice());
            statement.setInt(8, property.getId());
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
            throw new RuntimeException("PropertyDao.getAtmosphereById() says: " + e.getMessage());
        }
        return null; // Return null if no property is found
    }


    public void processAllProperties(Consumer<Property> consumer) {
        String sql = "SELECT * FROM " + TABLE;
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToProperty(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException("PropertyDao.processAllProperties() says: " + e.getMessage());
        }

    }

    public void processFilteredProperties(Consumer<Property> consumer, PropertyFilter filter) {
        try (
                PreparedStatement statement = filter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToProperty(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException("PropertyDao.processAllProperties() says: " + e.getMessage());
        }

    }


    public void buyProperty(int userId, int propertyId) {
        String sql = "UPDATE " + TABLE + " SET sold_to_user_id = ? WHERE property_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, propertyId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("PropertyDao.buyProperty() says: " + e.getMessage());
        }
    }



    /******************************************************************************************************************/
    public void processAvailablePropertiesByCelestialBodyId(int celestialBodyId, Consumer<Property> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE celestial_body_id = ? AND sold_to_user_id IS NULL AND";
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
    public void processPropertiesOwnedByUserId(int userId, Consumer<Property> consumer) {
        String sql = "SELECT * FROM " + TABLE + " WHERE sold_to_user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
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
        Object soldToUserId = resultSet.getObject("sold_to_user_id");

        int globalRegistryNb = resultSet.getInt("property_reg_nb");
        String address = resultSet.getString("address");
        BigDecimal squareMeters = resultSet.getBigDecimal("square_meters");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        return new Property(propertyId, celestialBodyId, soldToUserId == null ? null : (Integer) soldToUserId, globalRegistryNb, address, squareMeters, name, description, price);
    }


}
