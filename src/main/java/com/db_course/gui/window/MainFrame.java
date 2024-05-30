package com.db_course.gui.window;
import com.db_course.gui.entity_panels.ResidentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private JPanel entityPanelContainer;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Main Frame");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(200);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        entityPanelContainer = new JPanel();
        cardLayout = new CardLayout();
        entityPanelContainer.setLayout(cardLayout);

        addEntityPanel("Residents", new ResidentPanel());

        JButton residentButton = new JButton("Residents");
        residentButton.addActionListener((ActionEvent e) -> switchToPanel("Residents"));
        buttonPanel.add(residentButton);

        splitPane.setLeftComponent(buttonPanel);
        splitPane.setRightComponent(entityPanelContainer);

        add(splitPane);
    }

    private void addEntityPanel(String name, JPanel panel) {
        entityPanelContainer.add(panel, name);
    }

    private void switchToPanel(String name) {
        cardLayout.show(entityPanelContainer, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

