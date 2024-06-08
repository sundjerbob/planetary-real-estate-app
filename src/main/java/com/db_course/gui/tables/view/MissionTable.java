package com.db_course.gui.tables.view;

import com.db_course.dto.MissionDto;
import com.db_course.gui.tables.model.MissionTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MissionTable extends JTable {


    private final MissionTableModel tableModel;


    public MissionTable(MissionTableModel model) {
        super(model);
        this.tableModel = model;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = getSelectedRow();
                    if (row != -1) {
                        MissionDto selectedMission = tableModel.getMissionAt(row);
                        System.out.println("Selected Mission: " + selectedMission);
                    }
                }
            }
        });
    }

    public MissionDto getSelectedMission() {
        int row = getSelectedRow();
        if (row != -1) {
            return tableModel.getMissionAt(row);
        }
        return null;
    }
}
