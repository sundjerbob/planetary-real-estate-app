package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.CelestialTypeService;
import com.db_course.gui.filter_panels.CelestialTypeFilterPanel;
import com.db_course.gui.tables.model.CelestialTypeTableModel;
import com.db_course.gui.tables.view.CelestialTypeTable;

import javax.swing.*;
import java.awt.*;

public class CelestialTypePanel extends JPanel {

    public CelestialTypePanel() {
        super(new BorderLayout());

        CelestialTypeTableModel tableModel = new CelestialTypeTableModel();
        CelestialTypeTable table = new CelestialTypeTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        CelestialTypeFilterPanel filterPanel = new CelestialTypeFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);

        this.add(splitPane, BorderLayout.CENTER);

        // Set the initial divider location to 40% of the parent width
        splitPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int width = getWidth();
                splitPane.setDividerLocation((int) (width * 0.4)); // Set to 40% of the parent width
                splitPane.removeComponentListener(this); // Remove listener after setting the initial position
            }
        });

        // Fetch initial data
        CelestialTypeService.getInstance().processAllCelestialTypes(tableModel::addCelestialType);
    }
}
