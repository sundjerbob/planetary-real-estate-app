package com.db_course.gui.filter_panels.datatype_filter;

import com.db_course.gui.filter_panels.datatype_filter.operations.DateFilterOperations;

import javax.swing.*;
import java.awt.*;

public class DateFilterPanel extends JPanel {

    private JTextField startDateField;
    private JTextField endDateField;
    private JComboBox<DateFilterOperations> operationsComboBox;

    public DateFilterPanel() {
        setLayout(new FlowLayout());

        operationsComboBox = new JComboBox<>(DateFilterOperations.values());
        startDateField = new JTextField(10);
        endDateField = new JTextField(10);

        add(operationsComboBox);
        add(new JLabel("From:"));
        add(startDateField);
        add(new JLabel("To:"));
        add(endDateField);
    }

    public String getStartDate() {
        return startDateField.getText();
    }

    public void setStartDate(String startDate) {
        startDateField.setText(startDate);
    }

    public String getEndDate() {
        return endDateField.getText();
    }

    public void setEndDate(String endDate) {
        endDateField.setText(endDate);
    }

    public DateFilterOperations getSelectedOperation() {
        return (DateFilterOperations) operationsComboBox.getSelectedItem();
    }
}
