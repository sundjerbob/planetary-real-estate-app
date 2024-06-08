package com.db_course.gui.entity_panels.views;

import com.db_course.be.service.ElementService;
import com.db_course.gui.filter_panels.ElementFilterPanel;
import com.db_course.gui.tables.model.ElementTableModel;
import com.db_course.gui.tables.view.ElementTable;

import javax.swing.*;
import java.awt.*;

public class ElementPanel extends JPanel {

    public ElementPanel() {
        super(new BorderLayout());

        ElementTableModel tableModel = new ElementTableModel();
        ElementTable table = new ElementTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        ElementFilterPanel filterPanel = new ElementFilterPanel(tableModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, scrollPane);

        // Setting the initial divider location to 30% of the parent dimensions
        splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, evt -> {
            if (!splitPane.getClientProperty("initialized").equals(Boolean.TRUE)) {
                int totalWidth = splitPane.getWidth();
                int initialDividerLocation = (int) (totalWidth * 0.33);
                splitPane.setDividerLocation(initialDividerLocation);
                splitPane.putClientProperty("initialized", Boolean.TRUE);
            }
        });
        splitPane.putClientProperty("initialized", Boolean.FALSE);

        this.add(splitPane, BorderLayout.CENTER);

        // Fetch initial data
        ElementService.getInstance().processAllElements(tableModel::addElement);
    }
}
