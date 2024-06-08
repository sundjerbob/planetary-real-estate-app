package com.db_course.gui.tables.model;

import com.db_course.dto.AtmosphereDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class AtmosphereTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            "ID", "Max Temperature", "Min Temperature", "Atmosphere Height", "Ampere Pressure", "Celestial Body"
    };
    private final List<AtmosphereDto> atmospheres;
    private final TreeSet<Integer> selectedRows;

    public AtmosphereTableModel() {
        this.atmospheres = new ArrayList<>();
        this.selectedRows = new TreeSet<>();
    }

    public void addAtmosphere(AtmosphereDto atmosphere) {
        atmospheres.add(atmosphere);
        fireTableRowsInserted(atmospheres.size() - 1, atmospheres.size() - 1);
    }

    public AtmosphereDto getAtmosphereAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < atmospheres.size()) {
            return atmospheres.get(rowIndex);
        }
        return null;
    }

    public void clear() {
        atmospheres.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return atmospheres.size();
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
        AtmosphereDto atmosphere = atmospheres.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> atmosphere.getId();
            case 1 -> atmosphere.getMaxTemperature();
            case 2 -> atmosphere.getMinTemperature();
            case 3 -> atmosphere.getAtmosphereHeight();
            case 4 -> atmosphere.getAmperePressure();
            case 5 -> atmosphere.getCelestialBody();
            default -> null;
        };
    }
}
