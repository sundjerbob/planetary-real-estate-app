package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.AtmosphereElement;
import com.db_course.be.filter.entity_filters.impl.AtmosphereElementFilter;

import java.math.BigDecimal;
import java.sql.*;
import java.util.function.Consumer;

public class AtmosphereElementDao {

    private final Connection connection;

    private static final String TABLE = "ATMOSPHERES_ELEMENTS";


    public AtmosphereElementDao() {
        this.connection = DB_Client.getInstance().getConnection();

    }

    public void processAllAtmosphereElements(Consumer<AtmosphereElement> consumer) {

        String sql = "SELECT * FROM " + TABLE + " ORDER BY atmosphere_id ASC";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {

                consumer.accept(mapToAtmosphereElement(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException("AtmosphereElementDao.processAllAtmosphereElements() says " + e.getMessage());
        }

    }

    public void processFilteredAtmosphereElements(Consumer<AtmosphereElement> consumer, AtmosphereElementFilter filter) {


        try (
                PreparedStatement statement = filter.generatePreparedStatement();
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
