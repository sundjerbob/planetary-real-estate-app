package com.db_course.gui.interaction;

import com.db_course.be.service.UserService;
import com.db_course.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountForm extends JPanel {

    private final JTextField nameField;
    private final JTextField lastNameField;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;
    private final MainFrame mainFrame;

    public CreateAccountForm(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField(20);

        JButton createAccountButton = new JButton("Create Account");

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lastNameLabel, gbc);
        gbc.gridx = 1;
        add(lastNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        add(confirmPasswordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(createAccountButton, gbc);
    }

    private void createAccount() {
        String name = nameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            UserService.getInstance().createUser(username, name, lastName, password);

            JOptionPane.showMessageDialog(this, "Account created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.setContentPane(new LoginPanel(mainFrame));
            mainFrame.revalidate();
            mainFrame.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to create account", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }
}
