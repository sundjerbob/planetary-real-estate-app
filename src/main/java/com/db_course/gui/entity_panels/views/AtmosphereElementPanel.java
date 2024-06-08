package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.AtmosphereElementService;
import com.db_course.gui.filter_panels.AtmosphereElementFilterPanel;
import com.db_course.gui.tables.model.AtmosphereElementTableModel;
import com.db_course.gui.tables.view.AtmosphereElementTable;

import javax.swing.*;
import java.awt.*;

public class AtmosphereElementPanel extends JPanel {

    public AtmosphereElementPanel() {
        super(new BorderLayout());

        AtmosphereElementTableModel tableModel = new AtmosphereElementTableModel();
        AtmosphereElementTable table = new AtmosphereElementTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        AtmosphereElementFilterPanel filterPanel = new AtmosphereElementFilterPanel(tableModel);

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
        AtmosphereElementService.getInstance().processAllAtmosphereElements(tableModel::addAtmosphereElement);
    }
}
