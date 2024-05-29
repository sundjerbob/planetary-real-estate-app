package com.db_course.app.view.tables.view;

import com.db_course.app.view.tables.model.ResidentTableModel;
import com.db_course.dto.ResidentDto;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class PersonTable extends JTable {

    private ResidentTableModel residentTableModel;
    @Getter
    private ResidentDto selectedPerson;

    public PersonTable() {
        this(new ResidentTableModel());
    }

    public PersonTable(ResidentTableModel model) {
        super(model);
        this.residentTableModel = model;
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = getSelectedRow();
            if (selectedRow != -1) {
                selectedPerson = residentTableModel.getPersonAt(selectedRow);
            }
        });
    }



    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        if (isRowSelected(row)) {
            c.setBackground(Color.LIGHT_GRAY);
        } else {
            c.setBackground(Color.WHITE);
        }
        return c;
    }
}
