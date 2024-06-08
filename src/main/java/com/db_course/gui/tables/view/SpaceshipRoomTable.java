package com.db_course.gui.tables.view;

import com.db_course.dto.SpaceshipRoomDto;
import com.db_course.gui.tables.model.SpaceshipRoomTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpaceshipRoomTable extends JTable {
    private SpaceshipRoomDto selectedSpaceshipRoom;

    public SpaceshipRoomTable(SpaceshipRoomTableModel model) {
        super(model);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                    selectedSpaceshipRoom = model.getSpaceshipRoomAt(selectedRow);
                }
            }
        });
    }

    public SpaceshipRoomDto getSelectedSpaceshipRoom() {
        return selectedSpaceshipRoom;
    }
}
