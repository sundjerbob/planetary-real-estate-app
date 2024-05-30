package com.db_course.gui.tables.view;

import com.db_course.dto.ResidentDto;
import com.db_course.gui.tables.model.ResidentTableModel;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ResidentTable extends JTable {


    private final ResidentTableModel personTableModel;
    @Getter
    private ResidentDto selectedPerson;


    public ResidentTable(ResidentTableModel model) {
        super(model);
        this.personTableModel = model;
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = getSelectedRow();
            if (selectedRow != -1) {
                selectedPerson = personTableModel.getPersonAt(selectedRow);
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