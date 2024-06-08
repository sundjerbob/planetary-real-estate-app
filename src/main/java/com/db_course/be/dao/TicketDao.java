package com.db_course.be.dao;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.entity_model.Ticket;
import com.db_course.be.filter.entity_filters.impl.TicketFilter;

import java.sql.*;
import java.util.function.Consumer;

public class TicketDao {


    private final Connection connection;
    private static final String TABLE = "TICKETS";

    public TicketDao() {

        this.connection = DB_Client.getInstance().getConnection();

    }


    /******************************************************************************************************************/
    public void addTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO " + TABLE + " (departure_id, passenger_id, price, room_id, spaceship_id) VALUES (?, ?, ?, ?, ?)";
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
        String sql = "SELECT * FROM " + TABLE + " WHERE ticket_id = ?";
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
    public void processAllTickets(Consumer<Ticket> consumer) {
        String sql = "SELECT * FROM " + TABLE + " ORDER BY departure_id DESC";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next())
                consumer.accept(mapToTicket(resultSet));
        } catch (Exception e) {
            throw new RuntimeException("TicketDao.getAllTickets() says: " + e.getMessage());
        }
    }


    public void processFilteredTickets(Consumer<Ticket> consumer, TicketFilter filter) {

        try (
                PreparedStatement statement = filter.generatePreparedStatement();
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                consumer.accept(mapToTicket(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("TicketDao.processFilteredTickets() says: " + e.getMessage());
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
                resultSet.getBoolean("sold"),
                resultSet.getObject("passenger_id", Integer.class)
        );
    }


}
