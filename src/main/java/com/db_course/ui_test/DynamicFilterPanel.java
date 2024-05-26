package com.db_course.ui_test;
import com.db_course.dto.UserDto;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DynamicFilterPanel extends JPanel {
    private Map<String, FilterComponent> filters;

    public DynamicFilterPanel() {
        filters = new HashMap<>();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String[] fields = {"name", "lastName", "username"};
        for (String field : fields) {
            FilterComponent filterComponent = new StringFilterComponent(field);
            filters.put(field, filterComponent);

            add(new JLabel(field + ": "), gbc);
            gbc.gridx++;
            add(filterComponent.getComponent(), gbc);
            gbc.gridx = 0;
            gbc.gridy++;
        }
    }

    public Map<String, FilterComponent> getFilters() {
        return filters;
    }

    public boolean applyFilters(UserDto user) {
        return filters.values().stream().allMatch(filter -> filter.applyFilter(user));
    }
}
