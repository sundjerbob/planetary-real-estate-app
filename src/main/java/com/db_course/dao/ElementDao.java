package com.db_course.dao;

import com.db_course.entity_model.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class ElementDao {

    private final Connection connection;

    public ElementDao(Connection connection) {
        this.connection = connection;
    }


    public void processAllElements(Consumer<Element> consumer) throws SQLException {

        String sql = "SELECT element_id, name, min_percentage, max_percentage FROM ELEMENTS";

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next())
                consumer.accept(mapToElement(resultSet));

        } catch (SQLException e) {

            throw new SQLException("ElementDao.getAllElements() says: " + e.getMessage());

        }

    }

    private Element mapToElement(ResultSet resultSet) {
        try {
            int elementId = resultSet.getInt("element_id");
            String name = resultSet.getString("name");
            double minPercentage = resultSet.getDouble("min_percentage");
            double maxPercentage = resultSet.getDouble("max_percentage");

            return new Element(elementId, name, minPercentage, maxPercentage);
        } catch (SQLException e) {
            throw new RuntimeException("ElementDao.mapToElement() says: " + e.getMessage());
        }
    }
}
