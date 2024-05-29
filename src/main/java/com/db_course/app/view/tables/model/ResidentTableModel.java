package com.db_course.app.view.tables.model;

import com.db_course.dto.ResidentDto;
import com.db_course.service.ResidentService;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ResidentTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Full Name", "Gender", "Birth Date", "Death Date"};
    private final TreeSet<ResidentDto> personData;
    private List<ResidentDto> filteredData;

    public ResidentTableModel() {
        this.personData = new TreeSet<>((p1, p2) -> p1.getFullName().compareTo(p2.getFullName()));
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
        ResidentDto person = filteredData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return person.getFullName();
            case 1:
                return person.getGender();
            case 2:
                return person.getBirthDate();
            case 3:
                return person.getDeathDate();
            default:
                return null;
        }
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
                    .filter(person -> {
                        switch (column) {
                            case 0:
                                return person.getFullName().toLowerCase().contains(query.toLowerCase());
                            case 1:
                                return person.getGender().toLowerCase().contains(query.toLowerCase());
                            default:
                                return true;
                        }
                    }).collect(Collectors.toList());
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
