package com.db_course.gui.tables.view;

import com.db_course.dto.ResidentDto;
import com.db_course.gui.tables.model.ResidentTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ResidentTable extends JTable {

    private ResidentDto selectedResident;

    public ResidentTable(ResidentTableModel model) {
        super(model);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow >= 0) {
                    selectedResident = model.getResidentAt(selectedRow);
                }
            }
        });
    }

    public ResidentDto getSelectedResident() {
        return selectedResident;
    }
}
