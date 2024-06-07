package com.db_course.dao;

import com.db_course.db_config.DB_Client;
import com.db_course.entity_model.AtmosphereElement;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class AtmosphereElementDao {

    private final Connection connection;

    public AtmosphereElementDao() {
        this.connection = DB_Client.getInstance().getConnection();

    }

    public void processAllAtmosphereElements(Consumer<AtmosphereElement> consumer) {

        String sql = "SELECT atmosphere_id, element_id, percentage FROM ATMOSPHERES_ELEMENTS";

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToAtmosphereElement(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException("AtmosphereElementDao.processAllAtmosphereElements() says " + e.getMessage());
        }

    }


    private AtmosphereElement mapToAtmosphereElement(ResultSet resultSet) throws SQLException {

        int atmosphereId = resultSet.getInt("atmosphere_id");
        int elementId = resultSet.getInt("element_id");
        BigDecimal percentage = resultSet.getBigDecimal("percentage");

        return new AtmosphereElement(atmosphereId, elementId, percentage);
    }
}
