package com.db_course.gui.tables.view;

import com.db_course.dto.AtmosphereDto;
import com.db_course.gui.tables.model.AtmosphereTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AtmosphereTable extends JTable {
    private final AtmosphereTableModel tableModel;

    public AtmosphereTable(AtmosphereTableModel model) {
        super(model);
        this.tableModel = model;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = getSelectedRow();
                    if (row != -1) {
                        AtmosphereDto selectedAtmosphere = tableModel.getAtmosphereAt(row);
                        System.out.println("Selected Atmosphere: " + selectedAtmosphere);
                    }
                }
            }
        });
    }

    public AtmosphereDto getSelectedAtmosphere() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getAtmosphereAt(row);
        }
        return null;
    }
}
