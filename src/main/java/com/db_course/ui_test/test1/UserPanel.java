package com.db_course.ui_test.test1;


import com.db_course.dto.UserDto;
import com.db_course.service.UserService;
import com.db_course.ui_test.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UserPanel extends JPanel {
    private JTable userTable;
    private UserTableModel tableModel;
    private List<UserDto> users;

    public UserPanel() {
        setLayout(new BorderLayout());

        users = new ArrayList<>();
        tableModel = new UserTableModel(users);
        userTable = new JTable(tableModel);

        // Create a filter panel
        DynamicFilterPanel filterPanel = new DynamicFilterPanel();
        JButton filterButton = new JButton("Apply Filters");
        filterButton.addActionListener(e -> applyFilters(filterPanel));

        JPanel filterPanelWrapper = new JPanel(new BorderLayout());
        filterPanelWrapper.add(filterPanel, BorderLayout.CENTER);
        filterPanelWrapper.add(filterButton, BorderLayout.SOUTH);

        add(filterPanelWrapper, BorderLayout.NORTH);
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        loadUsers();
    }

    private void loadUsers() {
        UserService.getInstance().processAllUsers(users::add);
        tableModel.fireTableDataChanged();
    }

    private void applyFilters(DynamicFilterPanel filterPanel) {
        List<UserDto> filteredUsers = new ArrayList<>();
        for (UserDto user : users) {
            if (filterPanel.applyFilters(user)) {
                filteredUsers.add(user);
            }
        }
        users.clear();
        users.addAll(filteredUsers);
        tableModel.fireTableDataChanged();
    }
}
