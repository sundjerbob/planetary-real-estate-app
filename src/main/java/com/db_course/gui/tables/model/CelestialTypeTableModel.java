package com.db_course.gui.tables.model;

import com.db_course.dto.CelestialTypeDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CelestialTypeTableModel extends AbstractTableModel {
    private final List<CelestialTypeDto> celestialTypes;
    private final String[] columnNames = {"ID", "Name", "Description"};

    public CelestialTypeTableModel() {
        celestialTypes = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return celestialTypes.size();
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
        CelestialTypeDto celestialType = celestialTypes.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> celestialType.getId();
            case 1 -> celestialType.getName();
            case 2 -> celestialType.getDescription();
            default -> null;
        };
    }

    public void addCelestialType(CelestialTypeDto celestialType) {
        celestialTypes.add(celestialType);
        fireTableRowsInserted(celestialTypes.size() - 1, celestialTypes.size() - 1);
    }

    public void clear() {
        celestialTypes.clear();
        fireTableDataChanged();
    }

    public CelestialTypeDto getCelestialTypeAt(int rowIndex) {
        return celestialTypes.get(rowIndex);
    }
}
