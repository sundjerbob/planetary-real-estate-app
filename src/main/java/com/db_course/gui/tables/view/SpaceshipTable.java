package com.db_course.gui.tables.view;

import com.db_course.dto.SpaceshipDto;
import com.db_course.gui.tables.model.SpaceshipTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpaceshipTable extends JTable {

    private final SpaceshipTableModel tableModel;

    public SpaceshipTable(SpaceshipTableModel model) {
        super(model);
        this.tableModel = model;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = getSelectedRow();
                    if (row != -1) {
                        SpaceshipDto selectedSpaceship = tableModel.getSpaceshipAt(row);
                        System.out.println("Selected Spaceship: " + selectedSpaceship);
                    }
                }
            }
        });
    }

    public SpaceshipDto getSelectedSpaceship() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getSpaceshipAt(row);
        }
        return null;
    }
}
