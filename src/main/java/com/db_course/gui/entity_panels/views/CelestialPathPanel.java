package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.CelestialPathService;
import com.db_course.gui.filter_panels.CelestialPathFilterPanel;
import com.db_course.gui.tables.model.CelestialPathTableModel;
import com.db_course.gui.tables.view.CelestialPathTable;

import javax.swing.*;
import java.awt.*;

public class CelestialPathPanel extends JPanel {

    public CelestialPathPanel() {
        super(new BorderLayout());

        CelestialPathTableModel tableModel = new CelestialPathTableModel();
        CelestialPathTable table = new CelestialPathTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        CelestialPathFilterPanel filterPanel = new CelestialPathFilterPanel(tableModel);

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
        CelestialPathService.getInstance().processAllCelestialPaths(tableModel::addCelestialPath);
    }
}
