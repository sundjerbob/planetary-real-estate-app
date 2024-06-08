package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.SpaceshipService;
import com.db_course.gui.filter_panels.SpaceshipFilterPanel;
import com.db_course.gui.tables.model.SpaceshipTableModel;
import com.db_course.gui.tables.view.SpaceshipTable;

import javax.swing.*;
import java.awt.*;

public class SpaceshipPanel extends JPanel {

    public SpaceshipPanel() {
        super(new BorderLayout());

        SpaceshipTableModel tableModel = new SpaceshipTableModel();
        SpaceshipTable table = new SpaceshipTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        SpaceshipFilterPanel filterPanel = new SpaceshipFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);

        this.add(splitPane, BorderLayout.CENTER);

        // Set the initial divider location to 40% of the parent width
        splitPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int width = getWidth();
                splitPane.setDividerLocation((int) (width * 0.33)); // Set to 40% of the parent width
                splitPane.removeComponentListener(this); // Remove listener after setting the initial position
            }
        });

        // Fetch initial data
        SpaceshipService.getInstance().processAllSpaceships(tableModel::addSpaceship);
    }
}
