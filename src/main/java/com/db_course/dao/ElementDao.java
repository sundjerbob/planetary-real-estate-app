package com.db_course.dao;

import com.db_course.entity_model.Element;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ElementDao {

    private final Connection connection;

    public ElementDao(Connection connection) {
        this.connection = connection;
    }

    public List<Element> getAllElements() throws SQLException {
        List<Element> elementList = new ArrayList<>();

        // Prepare SQL query
        String sql = "SELECT element_id, name, min_percentage, max_percentage FROM ELEMENTS";

        try (
                // Create PreparedStatement
                PreparedStatement statement = connection.prepareStatement(sql);
                // Execute query and get ResultSet
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve element data from the result set
                int elementId = resultSet.getInt("element_id");
                String name = resultSet.getString("name");
                double minPercentage = resultSet.getDouble("min_percentage");
                double maxPercentage = resultSet.getDouble("max_percentage");

                // Create an Element object and add it to the list
                Element element = new Element(elementId, name, minPercentage, maxPercentage);
                elementList.add(element);
            }
        }

        return elementList;
    }
}
