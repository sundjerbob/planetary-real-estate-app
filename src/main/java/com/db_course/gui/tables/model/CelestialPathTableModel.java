package com.db_course.gui.tables.model;

import com.db_course.dto.CelestialPathDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class CelestialPathTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "ID", "Body A", "Body B", "Distance (km)", "Description"
    };
    private final List<CelestialPathDto> celestialPaths;
    private final TreeSet<Integer> selectedRows;

    public CelestialPathTableModel() {
        this.celestialPaths = new ArrayList<>();
        this.selectedRows = new TreeSet<>();
    }

    public void addCelestialPath(CelestialPathDto celestialPath) {
        celestialPaths.add(celestialPath);
        fireTableRowsInserted(celestialPaths.size() - 1, celestialPaths.size() - 1);
    }

    public CelestialPathDto getCelestialPathAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < celestialPaths.size()) {
            return celestialPaths.get(rowIndex);
        }
        return null;
    }

    public void clear() {
        celestialPaths.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return celestialPaths.size();
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
        CelestialPathDto celestialPath = celestialPaths.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> celestialPath.getId();
            case 1 -> celestialPath.getBodyA();
            case 2 -> celestialPath.getBodyB();
            case 3 -> celestialPath.getDistanceKm();
            case 4 -> celestialPath.getDescription();
            default -> null;
        };
    }
}
