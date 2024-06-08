package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.CelestialBodyResidentService;
import com.db_course.gui.filter_panels.CelestialBodyResidentFilterPanel;
import com.db_course.gui.tables.model.CelestialBodyResidentTableModel;
import com.db_course.gui.tables.view.CelestialBodyResidentTable;

import javax.swing.*;
import java.awt.*;

public class CelestialBodyResidentPanel extends JPanel {

    public CelestialBodyResidentPanel() {
        super(new BorderLayout());

        CelestialBodyResidentTableModel tableModel = new CelestialBodyResidentTableModel();
        CelestialBodyResidentTable table = new CelestialBodyResidentTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        CelestialBodyResidentFilterPanel filterPanel = new CelestialBodyResidentFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);

        this.add(splitPane, BorderLayout.CENTER);

        splitPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int width = getWidth();
                splitPane.setDividerLocation((int) (width * 0.4));
                splitPane.removeComponentListener(this);
            }
        });

        CelestialBodyResidentService.getInstance().processAllCelestialBodyResidents(tableModel::addCelestialBodyResident);
    }
}
