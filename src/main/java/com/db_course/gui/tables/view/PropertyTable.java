package com.db_course.gui.tables.view;

import com.db_course.dto.PropertyDto;
import com.db_course.gui.tables.model.PropertyTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PropertyTable extends JTable {
    private PropertyDto selectedProperty;

    public PropertyTable(PropertyTableModel model) {
        super(model);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                    selectedProperty = model.getPropertyAt(selectedRow);
                }
            }
        });
    }

    public PropertyDto getSelectedProperty() {
        return selectedProperty;
    }
}
