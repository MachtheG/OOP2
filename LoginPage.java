/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ooproject;

/**
 *
 * @author machn
 */

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

    // Declare components for the login form
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Constructor to create the Login GUI
    public LoginPage() {
        setTitle("Admin Login"); // Set the title of the window
        setSize(300, 150); // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application on window close
        setLayout(null); // Use absolute positioning for layout

        // Create and position username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 10, 80, 25); // Set position and size
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 10, 160, 25); // Set position and size
        add(usernameField);

        // Create and position password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25); // Set position and size
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 40, 160, 25); // Set position and size
        add(passwordField);

        // Create and position login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25); // Set position and size
        add(loginButton);

        // Add action listener to handle login button click event
        loginButton.addActionListener((ActionEvent e) -> {
            // Get the entered username and password
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            // Call the validateAdmin method from DatabaseManager to verify credentials
            if (DatabaseManager.validateAdmin(username, password)) {
                // If the login is successful
                JOptionPane.showMessageDialog(null, "Login successful!");
                // Open the HomePage window or next screen
                new HomePage().setVisible(true);
                dispose(); // Close the login window
            } else {
                // If the login fails
                JOptionPane.showMessageDialog(null, "Invalid login! Please try again.");
            }
        });
    }

    // Main method to launch the LoginPage
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true); // Make the login window visible
    }
}

