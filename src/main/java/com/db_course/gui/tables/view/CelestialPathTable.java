package com.db_course.gui.tables.view;

import com.db_course.dto.CelestialPathDto;
import com.db_course.gui.tables.model.CelestialPathTableModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CelestialPathTable extends JTable {

    private final CelestialPathTableModel tableModel;
    @Getter
    private CelestialPathDto selectedCelestialPath;

    public CelestialPathTable(CelestialPathTableModel tableModel) {
        super(tableModel);
        this.tableModel = tableModel;
        this.selectedCelestialPath = null;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                    selectedCelestialPath = tableModel.getCelestialPathAt(selectedRow);
                }
            }
        });
    }

}
