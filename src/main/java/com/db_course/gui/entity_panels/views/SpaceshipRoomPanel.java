package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.SpaceshipRoomService;
import com.db_course.gui.filter_panels.SpaceshipRoomFilterPanel;
import com.db_course.gui.tables.model.SpaceshipRoomTableModel;
import com.db_course.gui.tables.view.SpaceshipRoomTable;

import javax.swing.*;
import java.awt.*;

public class SpaceshipRoomPanel extends JPanel {

    public SpaceshipRoomPanel() {
        super(new BorderLayout());

        SpaceshipRoomTableModel tableModel = new SpaceshipRoomTableModel();
        SpaceshipRoomTable table = new SpaceshipRoomTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        SpaceshipRoomFilterPanel filterPanel = new SpaceshipRoomFilterPanel(tableModel);

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
        SpaceshipRoomService.getInstance().processAllSpaceshipRooms(tableModel::addSpaceshipRoom);
    }
}
