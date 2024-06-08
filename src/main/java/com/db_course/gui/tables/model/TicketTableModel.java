package com.db_course.gui.tables.model;

import com.db_course.dto.TicketDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class TicketTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            "ID", "Departure ID", "Spaceship", "Room Number", "Price", "Sold", "Passenger ID"
    };
    private final List<TicketDto> tickets;
    private final TreeSet<Integer> selectedRows;

    public TicketTableModel() {
        this.tickets = new ArrayList<>();
        this.selectedRows = new TreeSet<>();
    }

    public void addTicket(TicketDto ticket) {
        tickets.add(ticket);
        fireTableRowsInserted(tickets.size() - 1, tickets.size() - 1);
    }

    public TicketDto getTicketAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < tickets.size()) {
            return tickets.get(rowIndex);
        }
        return null;
    }

    public void clear() {
        tickets.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return tickets.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TicketDto ticket = tickets.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> ticket.getId();
            case 1 -> ticket.getDepartureId();
            case 2 -> ticket.getSpaceship();
            case 3 -> ticket.getRoomNb();
            case 4 -> ticket.getPrice();
            case 5 -> ticket.getPassenger().equals("None") ? "free" : "soled";
            case 6 -> ticket.getPassenger();
            default -> null;
        };
    }
}
