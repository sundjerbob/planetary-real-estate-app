package com.db_course.gui.entity_panels.view_container;

import com.db_course.gui.entity_panels.views.*;

import javax.swing.*;
import java.awt.*;

public class EntityContainerPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final JLabel titleLabel;

    public EntityContainerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        // Create title label
        titleLabel = new JLabel("Celestial Bodies", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));


        // Create buttons for switching panels
        JPanel buttonPanel = new JPanel();
        JButton celestialBodyButton = new JButton("Celestial Bodies");
        JButton departureButton = new JButton("Departures");
        JButton elementButton = new JButton("Elements");
        JButton spaceshipButton = new JButton("Spaceships");
        JButton celestialTypeButton = new JButton("Celestial Types");
        JButton atmosphereButton = new JButton("Atmospheres");
        JButton celestialBodyResidentButton = new JButton("Celestial Body Residents");
        JButton residentButton = new JButton("Residents");
        JButton missionButton = new JButton("Missions");


        buttonPanel.add(celestialBodyButton);
        buttonPanel.add(departureButton);
        buttonPanel.add(elementButton);
        buttonPanel.add(spaceshipButton);
        buttonPanel.add(celestialTypeButton);
        buttonPanel.add(atmosphereButton);
        buttonPanel.add(celestialBodyResidentButton);
        buttonPanel.add(residentButton);
        buttonPanel.add(missionButton);

        // Create card layout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add entity panels to card panel
        cardPanel.add(new CelestialBodyPanel(), "CelestialBodies");
        cardPanel.add(new DeparturePanel(), "Departures");
        cardPanel.add(new ElementPanel(), "Elements");
        cardPanel.add(new SpaceshipPanel(), "Spaceships");
        cardPanel.add(new CelestialTypePanel(), "CelestialTypes");
        cardPanel.add(new AtmospherePanel(), "Atmospheres");
        cardPanel.add(new CelestialBodyResidentPanel(), "CelestialBodyResidents");
        cardPanel.add(new ResidentPanel(), "Residents");
        cardPanel.add(new MissionPanel(), "Missions");

        // Add title and buttons to the top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Vertical stacking
        topPanel.add(titleLabel);
        topPanel.add(buttonPanel);

        add(topPanel);
        add(cardPanel);

        titleLabel.setPreferredSize(new Dimension(0, 50));
        buttonPanel.setPreferredSize(new Dimension(0, 50));

        celestialBodyButton.addActionListener(e -> showPanel("CelestialBodies", "Celestial Bodies"));
        departureButton.addActionListener(e -> showPanel("Departures", "Departures"));
        elementButton.addActionListener(e -> showPanel("Elements", "Elements"));
        spaceshipButton.addActionListener(e -> showPanel("Spaceships", "Spaceships"));
        celestialTypeButton.addActionListener(e -> showPanel("CelestialTypes", "Celestial Types"));
        atmosphereButton.addActionListener(e -> showPanel("Atmospheres", "Atmospheres"));
        celestialBodyResidentButton.addActionListener(e -> showPanel("CelestialBodyResidents", "Celestial Body Residents"));
        residentButton.addActionListener(e -> showPanel("Residents", "Residents"));
        missionButton.addActionListener(e -> showPanel("Missions", "Missions"));


        showPanel("CelestialBodies", "Celestial Bodies");
    }

    private void showPanel(String card, String title) {
        cardLayout.show(cardPanel, card);
        titleLabel.setText(title);
    }
}
