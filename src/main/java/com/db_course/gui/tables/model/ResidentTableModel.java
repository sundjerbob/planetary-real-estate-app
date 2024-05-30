package com.db_course.gui.tables.model;

import com.db_course.dto.ResidentDto;
import com.db_course.service.ResidentService;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ResidentTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Full Name", "Gender", "Birth Date", "Death Date"};
    private final TreeSet<ResidentDto> personData;
    private List<ResidentDto> filteredData;

    public ResidentTableModel() {
        this.personData = new TreeSet<>(Comparator.comparing(ResidentDto::getFullName).thenComparing(ResidentDto::getBirthDate));
        this.filteredData = new ArrayList<>();
        populateData();
    }

    private void populateData() {
        ResidentService.getInstance().processAllResidents(personData::add);
        filteredData = new ArrayList<>(personData);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return filteredData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ResidentDto resident = filteredData.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> resident.getFullName();
            case 1 -> resident.getGender();
            case 2 -> resident.getBirthDate();
            case 3 -> resident.getDeathDate();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void filterByString(String query, int column) {
        if (query == null || query.isEmpty()) {
            filteredData = new ArrayList<>(personData);
        } else {
            filteredData = personData.stream()
                    .filter(
                            person ->
                                    switch (column) {
                                        case 0 -> person.getFullName().toLowerCase().contains(query.toLowerCase());
                                        case 1 -> person.getGender().toLowerCase().contains(query.toLowerCase());
                                        default -> true;
                                    }
                    ).collect(Collectors.toList());
        }
        fireTableDataChanged();
    }

    public void filterByDate(LocalDate startDate, LocalDate endDate, int column) {
        filteredData = personData.stream()
                .filter(person -> {
                    LocalDate date = (column == 2) ? person.getBirthDate() : person.getDeathDate();
                    boolean afterStart = (startDate == null) || (date != null && !date.isBefore(startDate));
                    boolean beforeEnd = (endDate == null) || (date != null && !date.isAfter(endDate));
                    return afterStart && beforeEnd;
                }).collect(Collectors.toList());
        fireTableDataChanged();
    }

    public ResidentDto getPersonAt(int row) {
        return filteredData.get(row);
    }
}
