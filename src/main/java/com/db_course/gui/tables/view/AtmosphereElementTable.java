package com.db_course.gui.tables.view;

import com.db_course.dto.AtmosphereElementDto;
import com.db_course.gui.tables.model.AtmosphereElementTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AtmosphereElementTable extends JTable {

    private AtmosphereElementDto selectedAtmosphereElement;

    public AtmosphereElementTable(AtmosphereElementTableModel model) {
        super(model);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow >= 0) {
                    selectedAtmosphereElement = model.getAtmosphereElementAt(selectedRow);
                }
            }
        });
    }

    public AtmosphereElementDto getSelectedAtmosphereElement() {
        return selectedAtmosphereElement;
    }
}
