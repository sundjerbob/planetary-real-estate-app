package com.db_course.gui.tables.model;

import com.db_course.dto.CelestialBodyDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CelestialBodyTableModel extends AbstractTableModel {
    private final List<CelestialBodyDto> celestialBodies;
    private final String[] columnNames = {"ID", "Name", "Description", "Surface Pressure", "Surface Temperature Min",
            "Surface Temperature Max", "Core Temperature", "Explored", "Radiation Level", "Has Water",
            "Surface Area", "Mass", "Gravitational Field Height", "Moving Speed", "Rotation Speed",
            "Celestial Body Type", "Rotates Around Body"};

    public CelestialBodyTableModel() {
        celestialBodies = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return celestialBodies.size();
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
        CelestialBodyDto celestialBody = celestialBodies.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return celestialBody.getId();
            case 1:
                return celestialBody.getName();
            case 2:
                return celestialBody.getDescription();
            case 3:
                return celestialBody.getSurfacePressure();
            case 4:
                return celestialBody.getSurfaceTemperatureMin();
            case 5:
                return celestialBody.getSurfaceTemperatureMax();
            case 6:
                return celestialBody.getCoreTemperature();
            case 7:
                return celestialBody.isExplored();
            case 8:
                return celestialBody.getRadiationLevel();
            case 9:
                return celestialBody.isHasWater();
            case 10:
                return celestialBody.getSurfaceArea();
            case 11:
                return celestialBody.getMass();
            case 12:
                return celestialBody.getGravitationalFieldHeight();
            case 13:
                return celestialBody.getMovingSpeed();
            case 14:
                return celestialBody.getRotationSpeed();
            case 15:
                return celestialBody.getCelestialBodyType();
            case 16:
                return celestialBody.getRotatesAroundBody();
            default:
                return null;
        }
    }

    public void addCelestialBody(CelestialBodyDto celestialBody) {
        celestialBodies.add(celestialBody);
        fireTableRowsInserted(celestialBodies.size() - 1, celestialBodies.size() - 1);
    }

    public void clear() {
        celestialBodies.clear();
        fireTableDataChanged();
    }

    public CelestialBodyDto getCelestialBodyAt(int rowIndex) {
        return celestialBodies.get(rowIndex);
    }
}
