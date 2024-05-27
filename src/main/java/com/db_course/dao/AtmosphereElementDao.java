package com.db_course.dao;

import com.db_course.entity_model.AtmosphereElement;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtmosphereElementDao {

    private final Connection connection;

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
                BigDecimal percentage = resultSet.getBigDecimal("percentage");

                AtmosphereElement atmosphereElement = new AtmosphereElement(atmosphereId, elementId, percentage);
                atmosphereElementList.add(atmosphereElement);
            }
        }

        return atmosphereElementList;
    }
}
