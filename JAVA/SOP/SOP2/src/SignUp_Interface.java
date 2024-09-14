import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUp_Interface extends JFrame implements ActionListener {

    private JPanel rightPanel, leftPanel;
    private JLabel companyNameLabel, copyrightLabel, titleLabel, emailLabel, passwordLabel, confirmPasswordLabel, roleLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton signUpButton, backButton;
    private JComboBox<String> roleComboBox;

    public SignUp_Interface() {
        initComponents();
    }

    private void initComponents() {
        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SIGN UP");
        setPreferredSize(new Dimension(800, 500));

        // Main panel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(800, 500));

        // Right panel
        rightPanel = createRightPanel();
        mainPanel.add(rightPanel);
        rightPanel.setBounds(0, 0, 400, 500);

        // Left panel
        leftPanel = createLeftPanel();
        mainPanel.add(leftPanel);
        leftPanel.setBounds(400, 0, 400, 500);

        // Layout
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        pack();
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 102, 102));
        panel.setLayout(new BorderLayout());

        companyNameLabel = new JLabel("Company Name", SwingConstants.CENTER);
        companyNameLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 24));
        companyNameLabel.setForeground(Color.WHITE);

        copyrightLabel = new JLabel("© Store Name. All rights reserved.", SwingConstants.CENTER);
        copyrightLabel.setForeground(new Color(204, 204, 204));

        panel.add(companyNameLabel, BorderLayout.CENTER);
        panel.add(copyrightLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Title
        titleLabel = new JLabel("SIGN UP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 102, 102));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        // Email
        emailLabel = new JLabel("User ID:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(30);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 2;
        panel.add(emailField, gbc);

        // Password
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 4;
        panel.add(passwordField, gbc);

        // Confirm Password
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = 5;
        panel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(30);
        confirmPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 6;
        panel.add(confirmPasswordField, gbc);

        // Role
        roleLabel = new JLabel("Role");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = 7;
        panel.add(roleLabel, gbc);

        String[] roles = { "Cashier", "Sales Assistant", "Store Manager" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 8;
        panel.add(roleComboBox, gbc);

        // Sign Up Button
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        signUpButton.setBackground(new Color(0, 102, 102));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(this); // Add ActionListener
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 9;
        panel.add(signUpButton, gbc);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setBackground(new Color(0, 102, 102));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this); // Add ActionListener
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 10;
        panel.add(backButton, gbc);

        return panel;
    }

    private boolean validateInputs(String email, String password, String confirmPassword, String role) {
        // Basic validation (you can expand this as needed)
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields.");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return false;
        }

        return true;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signUpButton) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String role = roleComboBox.getSelectedItem().toString();

            if (validateInputs(email, password, confirmPassword, role)) {
                // You can add the user registration logic here
                JOptionPane.showMessageDialog(null, "Registration successful!");
                setVisible(false);
                new Login_Interface().setVisible(true);
            }
        }

        if (ae.getSource() == backButton) {
            setVisible(false);
            new Login_Interface().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignUp_Interface().setVisible(true));
    }
}
