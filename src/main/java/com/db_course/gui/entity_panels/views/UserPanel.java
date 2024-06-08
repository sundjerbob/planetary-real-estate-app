package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.UserService;
import com.db_course.gui.filter_panels.UserFilterPanel;
import com.db_course.gui.tables.model.UserTableModel;
import com.db_course.gui.tables.view.UserTable;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {

    public UserPanel() {
        super(new BorderLayout());

        UserTableModel tableModel = new UserTableModel();
        UserTable table = new UserTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        UserFilterPanel filterPanel = new UserFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);

        this.add(splitPane, BorderLayout.CENTER);

        // Set the initial divider location to 30% of the parent width
        splitPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int width = getWidth();
                splitPane.setDividerLocation((int) (width * 0.33)); // Set to 33% of the parent width
                splitPane.removeComponentListener(this); // Remove listener after setting the initial position
            }
        });

        // Fetch initial data
        UserService.getInstance().processAllUsers(tableModel::addUser);
    }
}
