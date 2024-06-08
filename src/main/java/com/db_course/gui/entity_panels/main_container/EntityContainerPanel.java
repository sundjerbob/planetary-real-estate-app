package com.db_course.gui.entity_panels.main_container;

import com.db_course.gui.entity_panels.views.CelestialBodyPanel;
import com.db_course.gui.entity_panels.views.DeparturePanel;

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

        buttonPanel.add(celestialBodyButton);
        buttonPanel.add(departureButton);

        // Create card layout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add entity panels to card panel
        cardPanel.add(new CelestialBodyPanel(), "CelestialBodies");
        cardPanel.add(new DeparturePanel(), "Departures");

        add(buttonPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        celestialBodyButton.addActionListener(e -> cardLayout.show(cardPanel, "CelestialBodies"));

        departureButton.addActionListener(e -> cardLayout.show(cardPanel, "Departures"));

        // Show the default panel
        cardLayout.show(cardPanel, "CelestialBodies");
    }
}
