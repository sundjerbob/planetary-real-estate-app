package com.db_course.gui.tables.model;

import com.db_course.dto.DepartureDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DepartureTableModel extends AbstractTableModel {
    private final List<DepartureDto> departures;

    public DepartureTableModel() {
        this.departures = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return departures.size();
    }

    @Override
    public int getColumnCount() {
        return 5; // Number of columns in DepartureDto
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DepartureDto departure = departures.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return departure.getId();
            case 1:
                return departure.getDepartureDate();
            case 2:
                return departure.getSpaceship();
            case 3:
                return departure.getCelestialOrigin();
            case 4:
                return departure.getCelestialDestination();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Departure Date";
            case 2:
                return "Spaceship";
            case 3:
                return "Celestial Origin";
            case 4:
                return "Celestial Destination";
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }

    public void addDeparture(DepartureDto departure) {
        departures.add(departure);
        fireTableRowsInserted(departures.size() - 1, departures.size() - 1);
    }

    public void clear() {
        int size = departures.size();
        departures.clear();
        if (size > 0) {
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public DepartureDto getDepartureAt(int row) {
        return departures.get(row);
    }
}
