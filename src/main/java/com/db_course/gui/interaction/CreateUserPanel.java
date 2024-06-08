package com.db_course.gui.interaction;

import com.db_course.be.service.UserService;
import com.db_course.dto.UserDto;
import com.db_course.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserPanel extends JPanel {
    private final JTextField nameField;
    private final JTextField lastNameField;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JPasswordField repeatPasswordField;

    public CreateUserPanel(MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        JLabel lastNameLabel = new JLabel("Last Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lastNameLabel, gbc);

        lastNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(lastNameField, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(passwordField, gbc);

        JLabel repeatPasswordLabel = new JLabel("Repeat Password:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(repeatPasswordLabel, gbc);

        repeatPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(repeatPasswordField, gbc);

        JButton createAccountButton = new JButton("Create Account");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(createAccountButton, gbc);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String lastName = lastNameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String repeatPassword = new String(repeatPasswordField.getPassword());

                if (!password.equals(repeatPassword)) {
                    JOptionPane.showMessageDialog(CreateUserPanel.this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                UserDto user = null;
                try {
                    user = UserService.getInstance().createUser(name, lastName, username, password);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(CreateUserPanel.this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
                if (user != null) {
                    JOptionPane.showMessageDialog(CreateUserPanel.this, "Account created successfully. Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.showLoginPanel();
                } else {
                    JOptionPane.showMessageDialog(CreateUserPanel.this, "Failed to create account", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
