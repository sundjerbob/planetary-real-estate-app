package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.DepartureService;
import com.db_course.gui.filter_panels.DepartureFilterPanel;
import com.db_course.gui.tables.model.DepartureTableModel;
import com.db_course.gui.tables.view.DepartureTable;

import javax.swing.*;
import java.awt.*;

public class DeparturePanel extends JPanel {

    public DeparturePanel() {
        super(new BorderLayout());

        DepartureTableModel tableModel = new DepartureTableModel();
        DepartureTable table = new DepartureTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        DepartureFilterPanel filterPanel = new DepartureFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);

        // Setting the initial divider location to 30% of the parent dimensions
        splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, evt -> {
            if (!splitPane.getClientProperty("initialized").equals(Boolean.TRUE)) {
                int totalWidth = splitPane.getWidth();
                int initialDividerLocation = (int) (totalWidth * 0.4);
                splitPane.setDividerLocation(initialDividerLocation);
                splitPane.putClientProperty("initialized", Boolean.TRUE);
            }
        });
        splitPane.putClientProperty("initialized", Boolean.FALSE);

        this.add(splitPane, BorderLayout.CENTER);

        // Fetch initial data
        DepartureService.getInstance().processAllDepartures(tableModel::addDeparture);
    }
}
