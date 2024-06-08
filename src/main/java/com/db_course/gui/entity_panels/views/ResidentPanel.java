package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.ResidentService;
import com.db_course.gui.filter_panels.ResidentFilterPanel;
import com.db_course.gui.tables.model.ResidentTableModel;
import com.db_course.gui.tables.view.ResidentTable;

import javax.swing.*;
import java.awt.*;

public class ResidentPanel extends JPanel {

    public ResidentPanel() {
        super(new BorderLayout());

        ResidentTableModel tableModel = new ResidentTableModel();
        ResidentTable table = new ResidentTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        ResidentFilterPanel filterPanel = new ResidentFilterPanel(tableModel);

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
        ResidentService.getInstance().processAllResidents(tableModel::addResident);
    }
}
