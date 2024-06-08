package com.db_course.gui.tables.view;

import com.db_course.dto.DepartureDto;
import com.db_course.gui.tables.model.DepartureTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DepartureTable extends JTable {

    private final DepartureTableModel tableModel;

    public DepartureTable(DepartureTableModel model) {
        super(model);
        this.tableModel = model;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = getSelectedRow();
                    if (row != -1) {
                        DepartureDto selectedDeparture = tableModel.getDepartureAt(row);
                        System.out.println("Selected Departure: " + selectedDeparture);
                    }
                }
            }
        });
    }

    public DepartureDto getSelectedDeparture() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getDepartureAt(row);
        }
        return null;
    }
}
