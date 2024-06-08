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
                        CelestialBodyResidentDto selectedResident = tableModel.getCelestialBodyResidentAt(row);
                        System.out.println("Selected Celestial Body Resident: " + selectedResident);
                    }
                }
            }
        });
    }

    public CelestialBodyResidentDto getSelectedCelestialBodyResident() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getCelestialBodyResidentAt(row);
        }
        return null;
    }
}
