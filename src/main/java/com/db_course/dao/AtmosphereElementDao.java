package com.db_course.dao;

import com.db_course.entity_model.AtmosphereElement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtmosphereElementDao {

    private Connection connection;

    public AtmosphereElementDao(Connection connection) {
        this.connection = connection;
    }

    public List<AtmosphereElement> getAllAtmosphereElements() throws SQLException {
        List<AtmosphereElement> atmosphereElementList = new ArrayList<>();

        String sql = "SELECT atmosphere_id, element_id, percentage FROM ATMOSPHERES_ELEMENTS";

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                int atmosphereId = resultSet.getInt("atmosphere_id");
                int elementId = resultSet.getInt("element_id");
                double percentage = resultSet.getDouble("percentage");

                AtmosphereElement atmosphereElement = new AtmosphereElement(atmosphereId, elementId, percentage);
                atmosphereElementList.add(atmosphereElement);
            }
        }

        return atmosphereElementList;
    }
}
