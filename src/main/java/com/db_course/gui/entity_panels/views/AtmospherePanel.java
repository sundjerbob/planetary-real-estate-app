package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.AtmosphereService;
import com.db_course.gui.filter_panels.AtmosphereFilterPanel;
import com.db_course.gui.tables.model.AtmosphereTableModel;
import com.db_course.gui.tables.view.AtmosphereTable;

import javax.swing.*;
import java.awt.*;

public class AtmospherePanel extends JPanel {

    public AtmospherePanel() {
        super(new BorderLayout());

        AtmosphereTableModel tableModel = new AtmosphereTableModel();
        AtmosphereTable table = new AtmosphereTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        AtmosphereFilterPanel filterPanel = new AtmosphereFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);

        this.add(splitPane, BorderLayout.CENTER);

        // Set the initial divider location to 30% of the parent width
        splitPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int width = getWidth();
                splitPane.setDividerLocation((int) (width * 0.33)); // Set to 40% of the parent width
                splitPane.removeComponentListener(this); // Remove listener after setting the initial position
            }
        });

        // Fetch initial data
        AtmosphereService.getInstance().processAllAtmospheres(tableModel::addAtmosphere);
    }
}
