package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.Departure;
import com.db_course.be.filter.entity_filters.impl.DepartureFilter;

import java.sql.*;
import java.util.function.Consumer;

public class DepartureDao {


    private final Connection connection;
    private static final String TABLE = "DEPARTURES";


    public DepartureDao() {
        this.connection = DB_Client.getInstance().getConnection();
    }


    /******************************************************************************************************************/
    public void processAllDepartures(Consumer<Departure> departureConsumer) {

        String sql = "SELECT * FROM " + TABLE;
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {

                Departure departure = mapToDeparture(resultSet);
                departureConsumer.accept(departure);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DepartureDao.processAllDepartures() says: " + e.getMessage());
        }
    }



    /******************************************************************************************************************/
    public void processFilteredDepartures(Consumer<Departure> departureConsumer, DepartureFilter filter) {

        try (
                PreparedStatement statement = filter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Departure departure = mapToDeparture(resultSet);
                departureConsumer.accept(departure);
            }

        } catch (SQLException e) {

            throw new RuntimeException("DepartureDao.processFilteredDepartures() says: " + e.getMessage());
        }
    }


    private Departure mapToDeparture(ResultSet resultSet) throws SQLException {

        int departureId = resultSet.getInt("departure_id");
        Timestamp departureDate = resultSet.getTimestamp("departure_datetime");
        int originBodyId = resultSet.getInt("celestial_origin_id");
        int destinationBodyId = resultSet.getInt("celestial_destination_id");
        int spaceshipId = resultSet.getInt("spaceship_id");

        Departure d = new Departure(
                departureId,
                departureDate.toLocalDateTime(),
                originBodyId,
                destinationBodyId,
                spaceshipId
        );
//        System.out.println(d);

        return d;


    }


}
