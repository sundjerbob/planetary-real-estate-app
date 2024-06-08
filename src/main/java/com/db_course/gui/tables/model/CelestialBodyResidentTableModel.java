package com.db_course.gui.tables.model;

import com.db_course.dto.CelestialBodyResidentDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CelestialBodyResidentTableModel extends AbstractTableModel {
    private final List<CelestialBodyResidentDto> celestialBodyResidents;
    private final String[] columnNames = {"ID", "Resident ID", "Celestial Body", "Resident From", "Resident Until"};

    public CelestialBodyResidentTableModel() {
        celestialBodyResidents = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return celestialBodyResidents.size();
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
        CelestialBodyResidentDto celestialBodyResident = celestialBodyResidents.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> celestialBodyResident.getId();
            case 1 -> celestialBodyResident.getResidentId();
            case 2 -> celestialBodyResident.getCelestialBody();
            case 3 -> celestialBodyResident.getResidentFrom();
            case 4 -> celestialBodyResident.getResidentUntil();
            default -> null;
        };
    }

    public void addCelestialBodyResident(CelestialBodyResidentDto celestialBodyResident) {
        celestialBodyResidents.add(celestialBodyResident);
        fireTableRowsInserted(celestialBodyResidents.size() - 1, celestialBodyResidents.size() - 1);
    }

    public void clear() {
        int size = celestialBodyResidents.size();
        celestialBodyResidents.clear();
        if (size > 0) {
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public CelestialBodyResidentDto getCelestialBodyResidentAt(int rowIndex) {
        return celestialBodyResidents.get(rowIndex);
    }
}
