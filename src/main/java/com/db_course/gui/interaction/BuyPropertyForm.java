package com.db_course.gui.interaction;

import com.db_course.be.service.CelestialBodyService;
import com.db_course.be.service.PropertyService;
import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.PropertyDto;
import com.db_course.dto.UserDto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BuyPropertyForm extends JDialog {

    private JComboBox<String> celestialBodyChooser;
    private JButton nextButton;
    private JList<PropertyDto> propertyList;
    private DefaultListModel<PropertyDto> propertyListModel;
    private JButton buyButton;

    private UserDto user;
    private List<CelestialBodyDto> celestialBodies;

    public BuyPropertyForm(Frame owner, UserDto user) {
        super(owner, "Buy Property", true);
        this.user = user;
        initializeComponents();
        layoutComponents();
        fetchCelestialBodies();
    }

    private void initializeComponents() {
        celestialBodyChooser = new JComboBox<>();
        nextButton = new JButton("Next");
        propertyListModel = new DefaultListModel<>();
        propertyList = new JList<>(propertyListModel);
        buyButton = new JButton("Buy");

        nextButton.addActionListener(e -> showAvailableProperties());
        buyButton.addActionListener(e -> buySelectedProperty());
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.add(new JLabel("Select Celestial Body:"));
        inputPanel.add(celestialBodyChooser);
        inputPanel.add(nextButton);

        JPanel propertiesPanel = new JPanel(new BorderLayout());
        propertiesPanel.add(new JLabel("Available Properties:"), BorderLayout.NORTH);
        propertiesPanel.add(new JScrollPane(propertyList), BorderLayout.CENTER);
        propertiesPanel.add(buyButton, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.NORTH);
        add(propertiesPanel, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(getOwner());
    }

    private void fetchCelestialBodies() {
        celestialBodies = new ArrayList<>();
        CelestialBodyService.getInstance().processAllHabitableCelestialBodies(celestialBody -> {
            celestialBodies.add(celestialBody);
            celestialBodyChooser.addItem(celestialBody.getName());
        });
    }

    private void showAvailableProperties() {
        propertyListModel.clear();

        String selectedCelestialBody = (String) celestialBodyChooser.getSelectedItem();
        CelestialBodyDto celestialBody = celestialBodies.stream()
                .filter(body -> body.getName().equals(selectedCelestialBody))
                .findFirst()
                .orElse(null);

        if (celestialBody != null) {
            PropertyService.getInstance().processAvailablePropertiesByCelestialBodyId(celestialBody.getId(), propertyListModel::addElement);
        }
    }

    private void buySelectedProperty() {
        PropertyDto selectedProperty = propertyList.getSelectedValue();
        if (selectedProperty != null) {
            PropertyService.getInstance().buyProperty(selectedProperty.getId(), user.getId());
            JOptionPane.showMessageDialog(this, "Property bought successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a property to buy.");
        }
    }
}
