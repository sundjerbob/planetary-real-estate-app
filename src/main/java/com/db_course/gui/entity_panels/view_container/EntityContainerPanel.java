package com.db_course.gui.entity_panels.view_container;

import com.db_course.gui.entity_panels.views.CelestialBodyPanel;
import com.db_course.gui.entity_panels.views.DeparturePanel;
import com.db_course.gui.entity_panels.views.ElementPanel;
import com.db_course.gui.entity_panels.views.SpaceshipPanel;
import com.db_course.gui.entity_panels.views.CelestialTypePanel;

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

        buttonPanel.add(celestialBodyButton);
        buttonPanel.add(departureButton);
        buttonPanel.add(elementButton);
        buttonPanel.add(spaceshipButton);
        buttonPanel.add(celestialTypeButton);

        // Create card layout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add entity panels to card panel
        cardPanel.add(new CelestialBodyPanel(), "CelestialBodies");
        cardPanel.add(new DeparturePanel(), "Departures");
        cardPanel.add(new ElementPanel(), "Elements");
        cardPanel.add(new SpaceshipPanel(), "Spaceships");
        cardPanel.add(new CelestialTypePanel(), "CelestialTypes");

        add(buttonPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        celestialBodyButton.addActionListener(e -> cardLayout.show(cardPanel, "CelestialBodies"));
        departureButton.addActionListener(e -> cardLayout.show(cardPanel, "Departures"));
        elementButton.addActionListener(e -> cardLayout.show(cardPanel, "Elements"));
        spaceshipButton.addActionListener(e -> cardLayout.show(cardPanel, "Spaceships"));
        celestialTypeButton.addActionListener(e -> cardLayout.show(cardPanel, "CelestialTypes"));

        // Show the default panel
        cardLayout.show(cardPanel, "CelestialBodies");
    }
}
