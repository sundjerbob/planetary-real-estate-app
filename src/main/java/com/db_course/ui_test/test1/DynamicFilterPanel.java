package com.db_course.ui_test.test1;


import com.db_course.dto.UserDto;
import com.db_course.entity_model.Mission;
import com.db_course.dto.DepartureDto;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DynamicFilterPanel extends JPanel {
    private Map<String, JTextField> filters;

    public DynamicFilterPanel() {
        setLayout(new GridLayout(0, 2));
        filters = new HashMap<>();
        createFilterFields();
    }

    private void createFilterFields() {
        addFilterField("ID", "id");
        addFilterField("Name", "name");
        addFilterField("Last Name", "lastName");
        addFilterField("Username", "username");
        // Add fields for Mission
        addFilterField("Mission ID", "missionId");
        addFilterField("Explored Body ID", "exploredBodyId");
        addFilterField("Spaceship ID", "spaceshipId");
        addFilterField("Mission Name", "missionName");
        // Add fields for DepartureDto
        addFilterField("Departure ID", "departureId");
        addFilterField("Spaceship ID (Departure)", "departureSpaceshipId");
        addFilterField("Departure Date", "departureDate");
        addFilterField("Destination", "destination");
    }

    private void addFilterField(String label, String key) {
        JLabel jLabel = new JLabel(label);
        JTextField jTextField = new JTextField();
        add(jLabel);
        add(jTextField);
        filters.put(key, jTextField);
    }


    public <T> boolean applyFilters(T item) {
        for (Map.Entry<String, JTextField> entry : filters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().getText().trim();
            if (!value.isEmpty() && !matchesFilter(item, key, value)) {
                return false;
            }
        }
        return true;
    }

    private <T> boolean matchesFilter(T item, String key, String value) {
        if (item instanceof UserDto) {
            return matchesUserFilter((UserDto) item, key, value);
        } else if (item instanceof Mission) {
            return matchesMissionFilter((Mission) item, key, value);
        } else if (item instanceof DepartureDto) {
            return matchesDepartureFilter((DepartureDto) item, key, value);
        }
        return false;
    }

    private boolean matchesUserFilter(UserDto user, String key, String value) {
        return switch (key) {
            case "id" -> String.valueOf(user.getId()).contains(value);
            case "name" -> user.getName().toLowerCase().contains(value.toLowerCase());
            case "lastName" -> user.getLastName().toLowerCase().contains(value.toLowerCase());
            case "username" -> user.getUsername().toLowerCase().contains(value.toLowerCase());
            default -> false;
        };
    }

    private boolean matchesMissionFilter(Mission mission, String key, String value) {
        return switch (key) {
            case "missionId" -> String.valueOf(mission.getId()).contains(value);
            case "exploredBodyId" -> String.valueOf(mission.getExploredBodyId()).contains(value);
            case "spaceshipId" -> String.valueOf(mission.getSpaceshipId()).contains(value);
            case "missionName" -> mission.getName().toLowerCase().contains(value.toLowerCase());
            // Add other fields if necessary
            default -> false;
        };
    }

    private boolean matchesDepartureFilter(DepartureDto departure, String key, String value) {
        return switch (key) {
            case "departureId" -> String.valueOf(departure.getId()).contains(value);
            case "departureSpaceshipId" -> String.valueOf(departure.getSpaceshipId()).contains(value);
            case "departureDate" -> departure.getDepartureDate().toString().contains(value);

            // Add other fields if necessary
            default -> false;
        };
    }
}
