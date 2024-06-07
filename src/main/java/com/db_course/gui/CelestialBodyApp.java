package com.db_course.gui;
import com.db_course.gui.entity_panels.CelestialBodyPanel;

import javax.swing.*;

public class CelestialBodyApp {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Celestial Bodies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        CelestialBodyPanel celestialBodyPanel = new CelestialBodyPanel();
        frame.add(celestialBodyPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CelestialBodyApp::createAndShowGUI);
    }
}
