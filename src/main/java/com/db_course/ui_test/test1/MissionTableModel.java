package com.db_course.ui_test.test1;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.List;
import com.db_course.entity_model.Mission;

public class MissionTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Explored Body ID", "Spaceship ID", "Name", "Description", "Start Date", "End Date", "Completed"};
    private final List<Mission> missions;

    public MissionTableModel(List<Mission> missions) {
        this.missions = missions;
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
        Mission mission = missions.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return mission.getId();
            case 1:
                return mission.getExploredBodyId();
            case 2:
                return mission.getSpaceshipId();
            case 3:
                return mission.getName();
            case 4:
                return mission.getDescription();
            case 5:
                return mission.getStartDate();
            case 6:
                return mission.getEndDate();
            case 7:
                return mission.isCompleted();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Make cells non-editable for now
    }
}
