package com.db_course.gui.tables.model;

import com.db_course.dto.SpaceshipDto;

import javax.swing.table.AbstractTableModel;
import java.util.TreeSet;

public class SpaceshipTableModel extends AbstractTableModel {
    private final TreeSet<SpaceshipDto> spaceships = new TreeSet<>((s1, s2) -> Integer.compare(s1.getSpaceshipId(), s2.getSpaceshipId()));
    private final String[] columnNames = {"ID", "Name", "Model", "Passenger Capacity", "Fuel Capacity", "Max Travel Range", "Traveling Speed", "Manufacturer"};

    public void addSpaceship(SpaceshipDto spaceship) {
        spaceships.add(spaceship);
        fireTableDataChanged();
    }

    public void clear() {
        spaceships.clear();
        fireTableDataChanged();
    }

    public SpaceshipDto getSpaceshipAt(int rowIndex) {
        return (SpaceshipDto) spaceships.toArray()[rowIndex];
    }

    @Override
    public int getRowCount() {
        return spaceships.size();
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
        SpaceshipDto spaceship = getSpaceshipAt(rowIndex);
        return switch (columnIndex) {
            case 0 -> spaceship.getSpaceshipId();
            case 1 -> spaceship.getName();
            case 2 -> spaceship.getModel();
            case 3 -> spaceship.getPassengerCapacity();
            case 4 -> spaceship.getFuelCapacity();
            case 5 -> spaceship.getMaxTravelRange();
            case 6 -> spaceship.getTravelingSpeed();
            case 7 -> spaceship.getManufacturer();
            default -> null;
        };
    }
}
