package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.MissionService;
import com.db_course.gui.filter_panels.MissionFilterPanel;
import com.db_course.gui.tables.model.MissionTableModel;
import com.db_course.gui.tables.view.MissionTable;

import javax.swing.*;
import java.awt.*;

public class MissionPanel extends JPanel {

    public MissionPanel() {
        super(new BorderLayout());

        MissionTableModel tableModel = new MissionTableModel();
        MissionTable table = new MissionTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        MissionFilterPanel filterPanel = new MissionFilterPanel(tableModel);

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
        MissionService.getInstance().processAllMissions(tableModel::addMission);
    }
}
