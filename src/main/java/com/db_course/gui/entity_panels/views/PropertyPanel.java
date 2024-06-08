package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.PropertyService;
import com.db_course.gui.filter_panels.PropertyFilterPanel;
import com.db_course.gui.tables.model.PropertyTableModel;
import com.db_course.gui.tables.view.PropertyTable;

import javax.swing.*;
import java.awt.*;

public class PropertyPanel extends JPanel {

    public PropertyPanel() {
        super(new BorderLayout());

        PropertyTableModel tableModel = new PropertyTableModel();
        PropertyTable table = new PropertyTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        PropertyFilterPanel filterPanel = new PropertyFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);

        this.add(splitPane, BorderLayout.CENTER);

        // Set the initial divider location to 30% of the parent width
        splitPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int width = getWidth();
                splitPane.setDividerLocation((int) (width * 0.33)); // Set to 30% of the parent width
                splitPane.removeComponentListener(this); // Remove listener after setting the initial position
            }
        });

        // Fetch initial data
        PropertyService.getInstance().processAllProperties(tableModel::addProperty);
    }
}
