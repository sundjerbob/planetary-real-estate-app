package com.db_course.gui.entity_panels;

import com.db_course.be.service.CelestialBodyService;
import com.db_course.gui.filter_panels.CelestialBodyFilterPanel;
import com.db_course.gui.tables.model.CelestialBodyTableModel;
import com.db_course.gui.tables.view.CelestialBodyTable;

import javax.swing.*;
import java.awt.*;

public class CelestialBodyPanel extends JPanel {
    private final CelestialBodyTableModel tableModel;

    public CelestialBodyPanel() {
        super(new BorderLayout());

        tableModel = new CelestialBodyTableModel();
        CelestialBodyTable table = new CelestialBodyTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        CelestialBodyFilterPanel filterPanel = new CelestialBodyFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);
        splitPane.setDividerLocation(250);

        this.add(splitPane, BorderLayout.CENTER);

        // Fetch initial data
        CelestialBodyService.getInstance().processAllCelestialBodies(tableModel::addCelestialBody);
    }
}
