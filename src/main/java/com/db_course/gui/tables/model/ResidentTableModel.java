package com.db_course.gui.tables.model;

import com.db_course.dto.ResidentDto;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ResidentTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Full Name", "Gender", "Birth Date", "Death Date"};
    private final List<ResidentDto> residents;
    private final TreeSet<Integer> selectedRows;

    public ResidentTableModel() {
        this.residents = new ArrayList<>();
        this.selectedRows = new TreeSet<>();
    }

    public void addResident(ResidentDto resident) {
        residents.add(resident);
        fireTableRowsInserted(residents.size() - 1, residents.size() - 1);
    }

    public ResidentDto getResidentAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < residents.size()) {
            return residents.get(rowIndex);
        }
        return null;
    }

    public void clear() {
        residents.clear();
        fireTableDataChanged();
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
        ResidentDto resident = residents.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> resident.getId();
            case 1 -> resident.getFullName();
            case 2 -> resident.getGender();
            case 3 -> resident.getBirthDate();
            case 4 -> resident.getDeathDate();
            default -> null;
        };
    }
}
