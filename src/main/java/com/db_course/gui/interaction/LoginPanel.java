package com.db_course.gui.interaction;

import com.db_course.be.service.UserService;
import com.db_course.dto.UserDto;
import com.db_course.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        constraints.gridx = 1;
        add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        add(passwordField, constraints);

        JButton loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        loginButton.addActionListener(e -> performLogin(mainFrame));
        add(loginButton, constraints);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> showCreateAccountForm(mainFrame));
        constraints.gridy = 3;
        add(createAccountButton, constraints);
    }

    private void performLogin(MainFrame mainFrame) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        UserDto user = UserService.getInstance().login(username, password);
        if (user != null) {
            mainFrame.onLoginSuccess(user);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showCreateAccountForm(MainFrame mainFrame) {
        // Implement the create account form and show it
    }
}