package com.db_course.gui.tables.model;

import com.db_course.dto.ElementDto;

import javax.swing.table.AbstractTableModel;
import java.util.TreeSet;

public class ElementTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "Name", "Description", "Min Percentage", "Max Percentage", "Radioactive", "Inert"};
    private final TreeSet<ElementDto> elements;

    public ElementTableModel() {
        this.elements = new TreeSet<>((e1, e2) -> Integer.compare(e1.getId(), e2.getId()));
    }

    public void addElement(ElementDto element) {
        elements.add(element);
        fireTableDataChanged();
    }

    public void clear() {
        elements.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return elements.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ElementDto element = (ElementDto) elements.toArray()[rowIndex];
        return switch (columnIndex) {
            case 0 -> element.getId();
            case 1 -> element.getName();
            case 2 -> element.getDescription();
            case 3 -> element.getMinPercentage();
            case 4 -> element.getMaxPercentage();
            case 5 -> element.isRadioactive();
            case 6 -> element.isInert();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ElementDto getElementAt(int row) {
        return (ElementDto) elements.toArray()[row];
    }
}
