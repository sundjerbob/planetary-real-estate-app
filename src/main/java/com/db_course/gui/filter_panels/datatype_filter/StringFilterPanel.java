package com.db_course.gui.filter_panels.datatype_filter;


import com.db_course.gui.filter_panels.datatype_filter.operations.StringFilterOperations;

import javax.swing.*;
import java.awt.*;

public class StringFilterPanel extends JPanel {

    private JTextField valueField;
    private JComboBox<StringFilterOperations> operationsComboBox;

    public StringFilterPanel() {
        setLayout(new FlowLayout());

        operationsComboBox = new JComboBox<>(StringFilterOperations.values());
        valueField = new JTextField(10);

        add(operationsComboBox);
        add(valueField);
    }

    public String getValue() {
        return valueField.getText();
    }

    public void setValue(String value) {
        valueField.setText(value);
    }

    public StringFilterOperations getSelectedOperation() {
        return (StringFilterOperations) operationsComboBox.getSelectedItem();
    }
}
