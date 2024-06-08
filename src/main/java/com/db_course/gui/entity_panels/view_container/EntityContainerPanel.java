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
        JButton propertyButton = new JButton("Properties");
        JButton spaceshipRoomButton = new JButton("Spaceship Rooms");
        JButton ticketButton = new JButton("Tickets");
        JButton usersButton = new JButton("Users");
        JButton celestialPathButton = new JButton("Celestial Paths");


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
        buttonPanel.add(propertyButton);
        buttonPanel.add(spaceshipRoomButton);
        buttonPanel.add(ticketButton);
        buttonPanel.add(usersButton);
        buttonPanel.add(celestialPathButton);

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
        cardPanel.add(new PropertyPanel(), "Properties");
        cardPanel.add(new SpaceshipRoomPanel(), "SpaceshipRooms");
        cardPanel.add(new TicketPanel(), "Tickets");
        cardPanel.add(new UserPanel(), "Users");
        cardPanel.add(new CelestialPathPanel(), "CelestialPaths");

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
        propertyButton.addActionListener(e -> cardLayout.show(cardPanel, "Properties"));
        spaceshipRoomButton.addActionListener(e -> cardLayout.show(cardPanel, "SpaceshipRooms"));
        ticketButton.addActionListener(e -> cardLayout.show(cardPanel, "Tickets"));
        usersButton.addActionListener(e -> cardLayout.show(cardPanel, "Users"));
        celestialPathButton.addActionListener(e -> cardLayout.show(cardPanel, "CelestialPaths"));
        // Show the default panel
        cardLayout.show(cardPanel, "CelestialBodies");
    }
}
