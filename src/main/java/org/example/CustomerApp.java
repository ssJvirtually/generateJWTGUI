package org.example;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomerApp {
    private static Map<String, String[]> customerData = new HashMap<>();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(CustomerApp::createAndShowGUI);
        loadCustomerData();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Customer Token Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(customerIdLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> customerIdField = new JComboBox<>(customerData.keySet().toArray(new String[0]));
        customerIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        customerIdField.setEditable(true);
        panel.add(customerIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel userNameLabel = new JLabel("User Name:");
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(userNameLabel, gbc);

        gbc.gridx = 1;
        JTextField userNameField = new JTextField();
        userNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(userNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton generateTokenButton = new JButton("Generate Token");
        generateTokenButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(generateTokenButton, gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        customerIdField.addActionListener(e -> {
            String selectedCustomerId = (String) customerIdField.getSelectedItem();
            if (customerData.containsKey(selectedCustomerId)) {
                String[] details = customerData.get(selectedCustomerId);
                userNameField.setText(details[0]);
                passwordField.setText(details[1]);
            } else {
                userNameField.setText("");
                passwordField.setText("");
            }
        });

        generateTokenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomerId = customerIdField.getSelectedItem().toString();
                String userName = userNameField.getText();
                String password = new String(passwordField.getPassword());

                // Placeholder for JWT generation logic
                generateJWT(selectedCustomerId, userName, password);
            }
        });
    }

    private static void loadCustomerData() {
        File csvFile = new File("users.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("customerId")) continue; // Skip header or empty lines
                String[] parts = line.split(",");
                String customerId = parts[0].trim();
                String userName = parts.length > 1 ? parts[1].trim() : "";
                String password = parts.length > 2 ? parts[2].trim() : "";
                customerData.put(customerId, new String[]{userName, password});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading customer data from CSV file: " + e.getMessage());
        }
    }

    private static void generateJWT(String customerId, String userName, String password) {
        // Placeholder logic for JWT generation
        System.out.println("Generating JWT for Customer ID: " + customerId + ", User Name: " + userName + ", Password: " + password);
        JOptionPane.showMessageDialog(null, "Token Generated: [Placeholder Token]");
    }
}