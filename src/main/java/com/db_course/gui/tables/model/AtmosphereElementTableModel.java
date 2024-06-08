package com.db_course.gui.tables.model;

import com.db_course.dto.AtmosphereElementDto;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class AtmosphereElementTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Atmosphere ID", "Celestial Body", "Element", "Percentage"};
    private final List<AtmosphereElementDto> atmosphereElements;
    private final TreeSet<Integer> selectedRows;

    public AtmosphereElementTableModel() {
        this.atmosphereElements = new ArrayList<>();
        this.selectedRows = new TreeSet<>();
    }

    public void addAtmosphereElement(AtmosphereElementDto atmosphereElement) {
        atmosphereElements.add(atmosphereElement);
        fireTableRowsInserted(atmosphereElements.size() - 1, atmosphereElements.size() - 1);
    }

    public AtmosphereElementDto getAtmosphereElementAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < atmosphereElements.size()) {
            return atmosphereElements.get(rowIndex);
        }
        return null;
    }

    public void clear() {
        atmosphereElements.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return atmosphereElements.size();
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
        AtmosphereElementDto atmosphereElement = atmosphereElements.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> atmosphereElement.getAtmosphereId();
            case 1 -> atmosphereElement.getCelestialBody();
            case 2 -> atmosphereElement.getElement();
            case 3 -> atmosphereElement.getPercentage();
            default -> null;
        };
    }
}
