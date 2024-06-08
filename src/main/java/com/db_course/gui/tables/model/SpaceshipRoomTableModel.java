package com.db_course.gui.tables.model;

import com.db_course.dto.SpaceshipRoomDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SpaceshipRoomTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            "Room ID", "Spaceship ID", "Room Number", "Perks", "Num Hibernation Capsules"
    };
    private final List<SpaceshipRoomDto> spaceshipRooms;
    private final TreeSet<Integer> selectedRows;

    public SpaceshipRoomTableModel() {
        this.spaceshipRooms = new ArrayList<>();
        this.selectedRows = new TreeSet<>();
    }

    public void addSpaceshipRoom(SpaceshipRoomDto spaceshipRoom) {
        spaceshipRooms.add(spaceshipRoom);
        fireTableRowsInserted(spaceshipRooms.size() - 1, spaceshipRooms.size() - 1);
    }

    public SpaceshipRoomDto getSpaceshipRoomAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < spaceshipRooms.size()) {
            return spaceshipRooms.get(rowIndex);
        }
        return null;
    }

    public void clear() {
        spaceshipRooms.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return spaceshipRooms.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SpaceshipRoomDto spaceshipRoom = spaceshipRooms.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> spaceshipRoom.getRoomId();
            case 1 -> spaceshipRoom.getSpaceship();
            case 2 -> spaceshipRoom.getRoomNumber();
            case 3 -> spaceshipRoom.getPerks();
            case 4 -> spaceshipRoom.getNumHibernationCapsules();
            default -> null;
        };
    }
}
