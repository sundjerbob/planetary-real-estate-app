
package com.db_course.ui_test.test1;


import com.db_course.dto.DepartureDto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DepartureTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Spaceship ID", "Departure Date", "Destination"};
    private final List<DepartureDto> departures;

    public DepartureTableModel(List<DepartureDto> departures) {
        this.departures = departures;
    }

    @Override
    public int getRowCount() {
        return departures.size();
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
        DepartureDto departure = departures.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> departure.getId();
            case 1 -> departure.getSpaceshipId();
            case 2 -> departure.getDepartureDate();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Make cells non-editable for now
    }
}
