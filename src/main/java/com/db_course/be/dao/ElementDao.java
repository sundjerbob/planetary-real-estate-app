package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.Element;
import com.db_course.be.filter.entity_filters.impl.ElementFilter;

import java.sql.*;
import java.util.function.Consumer;

public class ElementDao {

    private final Connection connection;
    private static final String TABLE = "ELEMENTS";

    public ElementDao() {
        this.connection = DB_Client.getInstance().getConnection();

    }


    /******************************************************************************************************************/
    public void processAllElements(Consumer<Element> consumer) {

        String sql = "SELECT *  FROM " + TABLE;

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next())
                consumer.accept(mapToElement(resultSet));

        } catch (SQLException e) {

            throw new RuntimeException("ElementDao.getAllElements() says: " + e.getMessage());

        }

    }

    /******************************************************************************************************************/
    public void processFilteredElements(Consumer<Element> consumer, ElementFilter filter) {

        try (
                PreparedStatement statement = filter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next())
                consumer.accept(mapToElement(resultSet));

        } catch (SQLException e) {

            throw new RuntimeException("ElementDao.processFilteredElements() says: " + e.getMessage());

        }

    }


    /******************************************************************************************************************/
    public Element getElementById(int elementId) {

        String sql = "SELECT * FROM " + TABLE + " WHERE element_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, elementId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return mapToElement(resultSet);
                return null;
            }

        } catch (SQLException e) {

            throw new RuntimeException("ElementDao.getElementById() says: " + e.getMessage());

        }

    }


    /******************************************************************************************************************/
    private Element mapToElement(ResultSet resultSet) throws SQLException {
        int elementId = resultSet.getInt("element_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        double minPercentage = resultSet.getDouble("min_percentage");
        double maxPercentage = resultSet.getDouble("max_percentage");
        boolean radioactive = resultSet.getBoolean("radioactive");
        boolean inert = resultSet.getBoolean("inert");

        return new Element(elementId, name, description, minPercentage, maxPercentage, radioactive, inert);
    }


}
