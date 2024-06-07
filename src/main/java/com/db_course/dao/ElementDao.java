package com.db_course.dao;

import com.db_course.db_config.DB_Client;
import com.db_course.entity_model.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class ElementDao {

    private final Connection connection;


    public ElementDao() {
        this.connection = DB_Client.getInstance().getConnection();

    }


    /******************************************************************************************************************/
    public void processAllElements(Consumer<Element> consumer) {

        String sql = "SELECT element_id, name, min_percentage, max_percentage FROM ELEMENTS";

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next())
                consumer.accept(mapToElement(resultSet));

        } catch (SQLException e) {

            throw new RuntimeException("ElementDao.getAllElements() says: " + e.getMessage());

        }

    }

    /******************************************************************************************************************/
    public Element getElementById(int elementId) {
        String sql = "SELECT element_id, name, min_percentage, max_percentage FROM ELEMENTS WHERE element_id = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ) {
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
    private Element mapToElement(ResultSet resultSet) {
        try {
            int elementId = resultSet.getInt("element_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double minPercentage = resultSet.getDouble("min_percentage");
            double maxPercentage = resultSet.getDouble("max_percentage");
            boolean radioactive = resultSet.getBoolean("radioactive");
            boolean inert = resultSet.getBoolean("inert");

            return new Element(elementId, name, description, minPercentage, maxPercentage, radioactive, inert);
        } catch (SQLException e) {
            throw new RuntimeException("ElementDao.mapToElement() says: " + e.getMessage());
        }
    }


}
