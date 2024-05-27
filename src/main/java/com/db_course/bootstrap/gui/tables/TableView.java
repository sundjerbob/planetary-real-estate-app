package com.db_course.bootstrap.gui.tables;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

public class TableView extends JTable {

    public TableView(Map<String, List<Object>> data) {
        super(new CustomTableModel(data));
    }

    static class CustomTableModel extends AbstractTableModel {
        private final String[] columnNames;
        private final Object[][] data;

        public CustomTableModel(Map<String, List<Object>> dataMap) {
            int rowCount = dataMap.values().stream().findFirst().orElseGet(List::of).size();
            columnNames = dataMap.keySet().toArray(String[]::new);
            data = new Object[rowCount][columnNames.length];
            for (int i = 0; i < rowCount; i++) {
                int j = 0;
                for (List<Object> column : dataMap.values()) {
                    data[i][j++] = column.get(i);
                }
            }
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {


            return false;
        }


    }
}