package com.db_course.gui.tables.view;

import com.db_course.dto.CelestialBodyResidentDto;
import com.db_course.gui.tables.model.CelestialBodyResidentTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CelestialBodyResidentTable extends JTable {
    private final CelestialBodyResidentTableModel tableModel;

    public CelestialBodyResidentTable(CelestialBodyResidentTableModel model) {
        super(model);
        this.tableModel = model;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = getSelectedRow();
                    if (row != -1) {
                        tableModel.setSelectedResident(row);
                        CelestialBodyResidentDto selectedResident = tableModel.getSelectedResident();
                        System.out.println("Selected Resident: " + selectedResident);
                    }
                }
            }
        });
    }

    public CelestialBodyResidentDto getSelectedResident() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getResidentAt(row);
        }
        return null;
    }
}
