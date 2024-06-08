package com.db_course.gui.tables.view;

import com.db_course.dto.CelestialTypeDto;
import com.db_course.gui.tables.model.CelestialTypeTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CelestialTypeTable extends JTable {
    private final CelestialTypeTableModel tableModel;

    public CelestialTypeTable(CelestialTypeTableModel model) {
        super(model);
        this.tableModel = model;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = getSelectedRow();
                    if (row != -1) {
                        CelestialTypeDto selectedType = tableModel.getCelestialTypeAt(row);
                        System.out.println("Selected Celestial Type: " + selectedType);
                    }
                }
            }
        });
    }

    public CelestialTypeDto getSelectedCelestialType() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getCelestialTypeAt(row);
        }
        return null;
    }
}
