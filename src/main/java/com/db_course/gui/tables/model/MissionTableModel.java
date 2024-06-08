package com.db_course.gui.tables.model;

import com.db_course.dto.MissionDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class MissionTableModel extends AbstractTableModel {
    private final List<MissionDto> missions;
    private final String[] columnNames = {
            "ID", "Celestial Body", "Spaceship", "Name", "Description", "Start Date", "End Date", "Completed"
    };

    public MissionTableModel() {
        missions = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return missions.size();
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
        MissionDto mission = missions.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> mission.getId();
            case 1 -> mission.getCelestialBody();
            case 2 -> mission.getSpaceship();
            case 3 -> mission.getName();
            case 4 -> mission.getDescription();
            case 5 -> mission.getStartDate();
            case 6 -> mission.getEndDate();
            case 7 -> mission.isCompleted();
            default -> null;
        };
    }

    public void addMission(MissionDto mission) {
        missions.add(mission);
        fireTableRowsInserted(missions.size() - 1, missions.size() - 1);
    }

    public void clear() {
        int size = missions.size();
        missions.clear();
        if (size > 0) {
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public MissionDto getMissionAt(int rowIndex) {
        return missions.get(rowIndex);
    }
}
