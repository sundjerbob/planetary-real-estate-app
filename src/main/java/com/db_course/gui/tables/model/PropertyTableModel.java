package com.db_course.gui.tables.model;

import com.db_course.dto.PropertyDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PropertyTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            "ID", "Celestial Body", "Sold To User ID", "Property Reg Nb", "Address",
            "Square Meters", "Name", "Description", "Price"
    };
    private final List<PropertyDto> properties;
    private final TreeSet<Integer> selectedRows;

    public PropertyTableModel() {
        this.properties = new ArrayList<>();
        this.selectedRows = new TreeSet<>();
    }

    public void addProperty(PropertyDto property) {
        properties.add(property);
        fireTableRowsInserted(properties.size() - 1, properties.size() - 1);
    }

    public PropertyDto getPropertyAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < properties.size()) {
            return properties.get(rowIndex);
        }
        return null;
    }

    public void clear() {
        properties.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return properties.size();
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
        PropertyDto property = properties.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> property.getId();
            case 1 -> property.getCelestialBody();
            case 2 -> property.getSoldToUser();
            case 3 -> property.getPropertyRegNb();
            case 4 -> property.getAddress();
            case 5 -> property.getSquareMeters();
            case 6 -> property.getName();
            case 7 -> property.getDescription();
            case 8 -> property.getPrice();
            default -> null;
        };
    }
}
