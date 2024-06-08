package com.db_course.gui;

import com.db_course.be.db_config.DB_Client;
import com.db_course.dto.UserDto;
import com.db_course.gui.entity_panels.view_container.EntityContainerPanel;
import com.db_course.gui.interaction.BuyPropertyForm;
import com.db_course.gui.interaction.BuyTicketsForm;
import com.db_course.gui.interaction.LoginPanel;
import com.db_course.gui.interaction.UserPropertiesForm;
import com.db_course.gui.interaction.UserTicketsForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private UserDto currentUser;
    private JToolBar toolBar;

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

        showLoginPanel();
    }

    private void showLoginPanel() {
        LoginPanel loginPanel = new LoginPanel(this);
        setContentPane(loginPanel);
        validate();
    }

    public void onLoginSuccess(UserDto user) {
        this.currentUser = user;
        showMainPanel();
    }

    private void showMainPanel() {
        EntityContainerPanel entityContainerPanel = new EntityContainerPanel();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(entityContainerPanel, BorderLayout.CENTER );
        setContentPane(panel);
        initializeToolBar();
        panel.add(toolBar, BorderLayout.NORTH);
        validate();
    }

    private void initializeToolBar() {
        toolBar = new JToolBar();
        JButton buyTicketsButton = new JButton("Buy Tickets");
        buyTicketsButton.addActionListener(e -> showBuyTicketsForm());
        toolBar.add(buyTicketsButton);

        JButton viewUserTicketsButton = new JButton("View My Tickets");
        viewUserTicketsButton.addActionListener(e -> showUserTicketsForm());
        toolBar.add(viewUserTicketsButton);

        JButton buyPropertyButton = new JButton("Buy Property");
        buyPropertyButton.addActionListener(e -> showBuyPropertyForm());
        toolBar.add(buyPropertyButton);

        JButton viewUserPropertiesButton = new JButton("My Properties");
        viewUserPropertiesButton.addActionListener(e -> showUserPropertiesForm());
        toolBar.add(viewUserPropertiesButton);
    }

    private void showBuyTicketsForm() {
        BuyTicketsForm buyTicketsForm = new BuyTicketsForm(this);
        buyTicketsForm.setVisible(true);
    }

    private void showUserTicketsForm() {
        UserTicketsForm userTicketsForm = new UserTicketsForm(this, currentUser);
        userTicketsForm.setVisible(true);
    }

    private void showBuyPropertyForm() {
        BuyPropertyForm buyPropertyForm = new BuyPropertyForm(this, currentUser);
        buyPropertyForm.setVisible(true);
    }

    private void showUserPropertiesForm() {
        UserPropertiesForm userPropertiesForm = new UserPropertiesForm(this, currentUser);
        userPropertiesForm.setVisible(true);
    }

    public UserDto getCurrentUser() {
        return currentUser;
    }
}
