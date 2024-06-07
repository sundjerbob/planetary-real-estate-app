package com.db_course.gui.tables.view;

import com.db_course.dto.CelestialBodyDto;
import com.db_course.gui.tables.model.CelestialBodyTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CelestialBodyTable extends JTable {


    private CelestialBodyTableModel tableModel;

    public CelestialBodyTable(CelestialBodyTableModel model) {
        super(model);
        this.tableModel = model;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = getSelectedRow();
                    if (row != -1) {
                        CelestialBodyDto selectedBody = tableModel.getCelestialBodyAt(row);
                        System.out.println("Selected Celestial Body: " + selectedBody);
                    }
                }
            }
        });
    }

    public CelestialBodyDto getSelectedCelestialBody() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getCelestialBodyAt(row);
        }
        return null;
    }
}
