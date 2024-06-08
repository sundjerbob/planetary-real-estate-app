package com.db_course.gui.tables.model;

import com.db_course.dto.CelestialBodyResidentDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CelestialBodyResidentTableModel extends AbstractTableModel {
    private final List<CelestialBodyResidentDto> residents;
    private final String[] columnNames = {"ID", "Resident ID", "Celestial Body", "Resident From", "Resident Until"};
    private CelestialBodyResidentDto selectedResident;

    public CelestialBodyResidentTableModel() {
        residents = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return residents.size();
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
        CelestialBodyResidentDto resident = residents.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return resident.getId();
            case 1:
                return resident.getResidentId();
            case 2:
                return resident.getCelestialBody();
            case 3:
                return resident.getResidentFrom();
            case 4:
                return resident.getResidentUntil();
            default:
                return null;
        }
    }

    public void addResident(CelestialBodyResidentDto resident) {
        residents.add(resident);
        fireTableRowsInserted(residents.size() - 1, residents.size() - 1);
    }

    public void clear() {
        residents.clear();
        fireTableDataChanged();
    }

    public CelestialBodyResidentDto getResidentAt(int rowIndex) {
        return residents.get(rowIndex);
    }

    public void setSelectedResident(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < residents.size()) {
            selectedResident = residents.get(rowIndex);
        }
    }

    public CelestialBodyResidentDto getSelectedResident() {
        return selectedResident;
    }
}
