package com.db_course.gui.tables.view;

import com.db_course.dto.ElementDto;
import com.db_course.gui.tables.model.ElementTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ElementTable extends JTable {

    private final ElementTableModel tableModel;

    public ElementTable(ElementTableModel model) {
        super(model);
        this.tableModel = model;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = getSelectedRow();
                    if (row != -1) {
                        ElementDto selectedElement = tableModel.getElementAt(row);
                        System.out.println("Selected Element: " + selectedElement);
                    }
                }
            }
        });
    }

    public ElementDto getSelectedElement() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getElementAt(row);
        }
        return null;
    }
}
