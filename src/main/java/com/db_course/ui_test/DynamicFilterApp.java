package com.db_course.ui_test;

import com.db_course.dto.UserDto;
import com.db_course.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DynamicFilterApp {
    private List<UserDto> allUsers = new ArrayList<>();
    private List<UserDto> filteredUsers = new ArrayList<>();
    private JTable userTable;
    private UserTableModel tableModel;
    private DynamicFilterPanel filterPanel;

    public DynamicFilterApp() {
        JFrame frame = new JFrame("Dynamic Filter Components");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        filterPanel = new DynamicFilterPanel();
        JButton filterButton = new JButton("Apply Filters");
        filterButton.addActionListener(e -> applyFilters());

        JPanel filterPanelWrapper = new JPanel(new BorderLayout());
        filterPanelWrapper.add(filterPanel, BorderLayout.CENTER);
        filterPanelWrapper.add(filterButton, BorderLayout.SOUTH);

        tableModel = new UserTableModel(filteredUsers);
        userTable = new JTable(tableModel);

        frame.add(filterPanelWrapper, BorderLayout.NORTH);
        frame.add(new JScrollPane(userTable), BorderLayout.CENTER);

        loadUsers();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadUsers() {
        UserService.getInstance().processAllUsers(user -> {
            allUsers.add(user);
            filteredUsers.add(user);
        });
        tableModel.fireTableDataChanged();
    }

    private void applyFilters() {
        filteredUsers.clear();
        for (UserDto user : allUsers) {
            if (filterPanel.applyFilters(user)) {
                filteredUsers.add(user);
            }
        }
        tableModel.fireTableDataChanged();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DynamicFilterApp::new);
    }
}
