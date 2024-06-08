package com.db_course.gui.tables.model;

import com.db_course.dto.UserDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class UserTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            "ID", "Name", "Last Name", "Username"
    };
    private final List<UserDto> users;
    private final TreeSet<Integer> selectedRows;

    public UserTableModel() {
        this.users = new ArrayList<>();
        this.selectedRows = new TreeSet<>();
    }

    public void addUser(UserDto user) {
        users.add(user);
        fireTableRowsInserted(users.size() - 1, users.size() - 1);
    }

    public UserDto getUserAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < users.size()) {
            return users.get(rowIndex);
        }
        return null;
    }

    public void clear() {
        users.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return users.size();
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
        UserDto user = users.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> user.getId();
            case 1 -> user.getName();
            case 2 -> user.getLastName();
            case 3 -> user.getUsername();
            default -> null;
        };
    }
}
