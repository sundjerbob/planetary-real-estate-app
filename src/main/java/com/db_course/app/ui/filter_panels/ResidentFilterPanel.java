package com.db_course.app.ui.filter_panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ResidentFilterPanel extends JPanel {

    private JTextField filterField;
    private JComboBox<String> filterColumnCombo;
    private JButton filterButton;
    private JButton clearFilterButton;
    private JTextField startDateField;
    private JTextField endDateField;
    private JComboBox<String> dateColumnCombo;

    public ResidentFilterPanel(String[] stringColumns, String[] dateColumns, ActionListener filterAction, ActionListener clearAction) {
        setLayout(new FlowLayout());

        // Components for string filtering
        filterField = new JTextField(20);
        filterColumnCombo = new JComboBox<>(stringColumns);
        filterButton = new JButton("Filter");
        clearFilterButton = new JButton("Clear Filter");

        add(new JLabel("Search:"));
        add(filterField);
        add(filterColumnCombo);
        add(filterButton);
        add(clearFilterButton);

        // Components for date range filtering
        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        dateColumnCombo = new JComboBox<>(dateColumns);

        add(new JLabel("Start Date:"));
        add(startDateField);
        add(new JLabel("End Date:"));
        add(endDateField);
        add(dateColumnCombo);

        // Set up actions
        filterButton.addActionListener(filterAction);
        clearFilterButton.addActionListener(clearAction);
    }

    public String getFilterText() {
        return filterField.getText().trim();
    }

    public int getSelectedColumnIndex() {
        return filterColumnCombo.getSelectedIndex();
    }

    public LocalDate getStartDate() {
        try {
            return LocalDate.parse(startDateField.getText().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDate getEndDate() {
        try {
            return LocalDate.parse(endDateField.getText().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public int getSelectedDateColumnIndex() {
        return dateColumnCombo.getSelectedIndex();
    }

    public void clearFilterText() {
        filterField.setText("");
        startDateField.setText("");
        endDateField.setText("");
    }
}
