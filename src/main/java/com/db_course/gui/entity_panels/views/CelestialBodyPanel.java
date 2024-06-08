package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.CelestialBodyService;
import com.db_course.gui.filter_panels.CelestialBodyFilterPanel;
import com.db_course.gui.tables.model.CelestialBodyTableModel;
import com.db_course.gui.tables.view.CelestialBodyTable;

import javax.swing.*;
import java.awt.*;

public class CelestialBodyPanel extends JPanel {

    public CelestialBodyPanel() {
        super(new BorderLayout());

        CelestialBodyTableModel tableModel = new CelestialBodyTableModel();
        CelestialBodyTable table = new CelestialBodyTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        CelestialBodyFilterPanel filterPanel = new CelestialBodyFilterPanel(tableModel);

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
        CelestialBodyService.getInstance().processAllCelestialBodies(tableModel::addCelestialBody);
    }
}
