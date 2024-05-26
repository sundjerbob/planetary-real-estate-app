package com.db_course.ui_test;
import com.db_course.dto.UserDto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private final List<UserDto> users;
    private final String[] columnNames = {"ID", "Name", "Last Name", "Username"};

    public UserTableModel(List<UserDto> users) {
        this.users = users;
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
