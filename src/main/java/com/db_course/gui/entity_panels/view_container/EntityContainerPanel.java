package com.db_course.gui.entity_panels.view_container;

import com.db_course.gui.entity_panels.views.*;

import javax.swing.*;
import java.awt.*;

public class EntityContainerPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public EntityContainerPanel() {
        setLayout(new BorderLayout());

        // Create buttons for switching panels
        JPanel buttonPanel = new JPanel();
        JButton celestialBodyButton = new JButton("Celestial Bodies");
        JButton departureButton = new JButton("Departures");
        JButton elementButton = new JButton("Elements");
        JButton spaceshipButton = new JButton("Spaceships");
        JButton celestialTypeButton = new JButton("Celestial Types");
        JButton atmosphereButton = new JButton("Atmospheres");
        JButton celestialBodyResidentButton = new JButton("Celestial Body Residents");
        JButton missionButton = new JButton("Missions");
        JButton residentButton = new JButton("Residents");
        JButton atmosphereElementButton = new JButton("Atmosphere Elements");

        buttonPanel.add(celestialBodyButton);
        buttonPanel.add(departureButton);
        buttonPanel.add(elementButton);
        buttonPanel.add(spaceshipButton);
        buttonPanel.add(celestialTypeButton);
        buttonPanel.add(atmosphereButton);
        buttonPanel.add(celestialBodyResidentButton);
        buttonPanel.add(missionButton);
        buttonPanel.add(residentButton);
        buttonPanel.add(atmosphereElementButton);

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
        cardPanel.add(new MissionPanel(), "Missions");
        cardPanel.add(new ResidentPanel(), "Residents");
        cardPanel.add(new AtmosphereElementPanel(), "AtmosphereElements");

        add(buttonPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        celestialBodyButton.addActionListener(e -> cardLayout.show(cardPanel, "CelestialBodies"));
        departureButton.addActionListener(e -> cardLayout.show(cardPanel, "Departures"));
        elementButton.addActionListener(e -> cardLayout.show(cardPanel, "Elements"));
        spaceshipButton.addActionListener(e -> cardLayout.show(cardPanel, "Spaceships"));
        celestialTypeButton.addActionListener(e -> cardLayout.show(cardPanel, "CelestialTypes"));
        atmosphereButton.addActionListener(e -> cardLayout.show(cardPanel, "Atmospheres"));
        celestialBodyResidentButton.addActionListener(e -> cardLayout.show(cardPanel, "CelestialBodyResidents"));
        missionButton.addActionListener(e -> cardLayout.show(cardPanel, "Missions"));
        residentButton.addActionListener(e -> cardLayout.show(cardPanel, "Residents"));
        atmosphereElementButton.addActionListener(e -> cardLayout.show(cardPanel, "AtmosphereElements"));

        // Show the default panel
        cardLayout.show(cardPanel, "CelestialBodies");
    }
}
