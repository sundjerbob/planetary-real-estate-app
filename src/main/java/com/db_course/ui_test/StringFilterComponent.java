package com.db_course.ui_test;

import com.db_course.dto.UserDto;

import javax.swing.*;

public class StringFilterComponent implements FilterComponent {
    private JTextField textField;
    private String fieldName;

    public StringFilterComponent(String fieldName) {
        this.fieldName = fieldName;
        textField = new JTextField(15);
    }

    @Override
    public JComponent getComponent() {
        return textField;
    }

    @Override
    public boolean applyFilter(UserDto user) {
        String filterText = textField.getText();
        if (filterText.isEmpty()) {
            return true;
        }
        switch (fieldName) {
            case "name":
                return user.getName() != null && user.getName().contains(filterText);
            case "lastName":
                return user.getLastName() != null && user.getLastName().contains(filterText);
            case "username":
                return user.getUsername() != null && user.getUsername().contains(filterText);
            default:
                return false;
        }
    }
}
