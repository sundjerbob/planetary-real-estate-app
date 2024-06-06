package com.db_course.dao;

import com.db_course.db_config.DB_Client;
import com.db_course.entity_model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class TicketDao {


    private final Connection connection;


    public TicketDao() {

        this.connection = DB_Client.getInstance().getConnection();

    }


    /******************************************************************************************************************/
    public void addTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO TICKETS (departure_id, passenger_id, price, room_id, spaceship_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticket.getDepartureId());
            statement.setInt(2, ticket.getPassengerId());
            statement.setBigDecimal(3, ticket.getPrice());
            statement.setInt(4, ticket.getRoomId());
            statement.setInt(5, ticket.getSpaceshipId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("TicketDao.addTicket() says: " + e.getMessage());

        }
    }


    /******************************************************************************************************************/
    public Ticket getTicket(int ticketId) throws SQLException {
        String sql = "SELECT * FROM TICKETS WHERE ticket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticketId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return mapToTicket(resultSet);
                return null;

            }
        } catch (Exception e) {
            throw new RuntimeException("TicketDao.getTicket() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public void getAllTickets(Consumer<Ticket> consumer) throws SQLException {
        String sql = "SELECT * FROM TICKETS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next())
                consumer.accept(mapToTicket(resultSet));
        } catch (Exception e) {
            throw new RuntimeException("TicketDao.getAllTickets() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    public void updateTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE TICKETS SET departure_id = ?, passenger_id = ?, price = ?, room_id = ?, spaceship_id = ? WHERE ticket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticket.getDepartureId());
            statement.setInt(2, ticket.getPassengerId());
            statement.setBigDecimal(3, ticket.getPrice());
            statement.setInt(4, ticket.getRoomId());
            statement.setInt(5, ticket.getSpaceshipId());
            statement.setInt(6, ticket.getId());
            statement.executeUpdate();
        }
    }


    /******************************************************************************************************************/
    public void deleteTicket(int ticketId) throws SQLException {
        String sql = "DELETE FROM TICKETS WHERE ticket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticketId);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("TicketDao.deleteTicket() says: " + e.getMessage());
        }
    }


    /******************************************************************************************************************/
    private Ticket mapToTicket(ResultSet resultSet) throws SQLException {

        return new Ticket(
                resultSet.getInt("ticket_id"),
                resultSet.getInt("departure_id"),
                resultSet.getInt("spaceship_id"),
                resultSet.getInt("room_id"),
                resultSet.getBigDecimal("price"),
                resultSet.getBoolean("soled"),
                resultSet.getObject("passenger_id", Integer.class)
        );
    }


}
