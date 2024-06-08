package com.db_course.gui;

import com.db_course.be.db_config.DB_Client;
import com.db_course.gui.entity_panels.view_container.EntityContainerPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Celestial App");

        // Set the window to be maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Are you sure you want to exit?", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    dispose();
                    DB_Client.getInstance().disconnect();
                }
            }
        });

        EntityContainerPanel entityContainerPanel = new EntityContainerPanel();
        add(entityContainerPanel);
    }

}
