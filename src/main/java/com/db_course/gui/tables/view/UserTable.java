package com.db_course.gui.tables.view;

import com.db_course.dto.UserDto;
import com.db_course.gui.tables.model.UserTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserTable extends JTable {
    private UserDto selectedUser;

    public UserTable(UserTableModel model) {
        super(model);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                    selectedUser = model.getUserAt(selectedRow);
                }
            }
        });
    }

    public UserDto getSelectedUser() {
        return selectedUser;
    }
}
