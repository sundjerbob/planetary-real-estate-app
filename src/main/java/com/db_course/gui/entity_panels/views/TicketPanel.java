package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.TicketService;
import com.db_course.gui.filter_panels.TicketFilterPanel;
import com.db_course.gui.tables.model.TicketTableModel;
import com.db_course.gui.tables.view.TicketTable;

import javax.swing.*;
import java.awt.*;

public class TicketPanel extends JPanel {

    public TicketPanel() {
        super(new BorderLayout());

        TicketTableModel tableModel = new TicketTableModel();
        TicketTable table = new TicketTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        TicketFilterPanel filterPanel = new TicketFilterPanel(tableModel);

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
        TicketService.getInstance().processAllTickets(tableModel::addTicket);
    }
}
