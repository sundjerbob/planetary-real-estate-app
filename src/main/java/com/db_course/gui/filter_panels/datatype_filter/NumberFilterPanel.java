package com.db_course.gui.filter_panels.datatype_filter;


import com.db_course.gui.filter_panels.datatype_filter.operations.NumberFilterOperations;

import javax.swing.*;
import java.awt.*;

public class NumberFilterPanel extends JPanel {

    private JComboBox<NumberFilterOperations> operationsCombo;
    private JTextField valueField;

    public NumberFilterPanel() {
        setLayout(new FlowLayout());

        operationsCombo = new JComboBox<>(NumberFilterOperations.values());
        valueField = new JTextField(10);

        add(new JLabel("Operation:"));
        add(operationsCombo);
        add(new JLabel("Value:"));
        add(valueField);
    }

    public NumberFilterOperations getSelectedOperation() {
        return (NumberFilterOperations) operationsCombo.getSelectedItem();
    }

    public String getValue() {
        return valueField.getText().trim();
    }
}
