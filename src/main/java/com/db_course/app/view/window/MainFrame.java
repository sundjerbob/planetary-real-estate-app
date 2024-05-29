package com.db_course.app.view.window;

import com.db_course.ui_test.test1.DeparturePanel;
import com.db_course.ui_test.test1.MissionPanel;
import com.db_course.ui_test.test1.UserPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Database Course Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and add the main panel (with card layout for different views)
        JPanel mainPanel = new JPanel(new CardLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Create a side panel with navigation buttons
        JPanel sidePanel = new JPanel(new GridLayout(0, 1));
        JButton userButton = new JButton("Users");
        JButton missionButton = new JButton("Missions");
        JButton departureButton = new JButton("Departures");

        sidePanel.add(userButton);
        sidePanel.add(missionButton);
        sidePanel.add(departureButton);
        add(sidePanel, BorderLayout.WEST);

        // Create panels for different functionalities
        UserPanel userPanel = new UserPanel();
        MissionPanel missionPanel = new MissionPanel();
        DeparturePanel departurePanel = new DeparturePanel();

        mainPanel.add(userPanel, "Users");
        mainPanel.add(missionPanel, "Missions");
        mainPanel.add(departurePanel, "Departures");

        // Add action listeners to buttons to switch panels
        userButton.addActionListener(e -> switchPanel(mainPanel, "Users"));
        missionButton.addActionListener(e -> switchPanel(mainPanel, "Missions"));
        departureButton.addActionListener(e -> switchPanel(mainPanel, "Departures"));
    }

    private void switchPanel(JPanel mainPanel, String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, panelName);
    }

}
