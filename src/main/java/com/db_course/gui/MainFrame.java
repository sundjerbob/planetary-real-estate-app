package com.db_course.gui;

import com.db_course.be.db_config.DB_Client;
import com.db_course.dto.UserDto;
import com.db_course.gui.entity_panels.view_container.EntityContainerPanel;
import com.db_course.gui.interaction.CreateUserPanel;
import com.db_course.gui.interaction.LoginPanel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    @Setter
    @Getter
    private UserDto loggedInUser;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public MainFrame() {
        setTitle("Celestial App");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(MainFrame.this,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    DB_Client.getInstance().disconnect();
                    System.exit(0);
                }
            }
        });

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(this);
        CreateUserPanel createUserPanel = new CreateUserPanel(this);
        EntityContainerPanel entityContainerPanel = new EntityContainerPanel();

        cardPanel.add(loginPanel, "LoginPanel");
        cardPanel.add(createUserPanel, "CreateUserPanel");
        cardPanel.add(entityContainerPanel, "EntityContainerPanel");

        add(cardPanel);
        showLoginPanel();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void showLoginPanel() {
        cardLayout.show(cardPanel, "LoginPanel");
    }

    public void showCreateUserPanel() {
        cardLayout.show(cardPanel, "CreateUserPanel");
    }

    public void showMainPanel() {
        cardLayout.show(cardPanel, "EntityContainerPanel");
    }

}
