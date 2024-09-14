import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login_Interface extends JFrame implements ActionListener {

    private JPanel rightPanel, leftPanel;
    private JLabel companyNameLabel, copyrightLabel, titleLabel, emailLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private JComboBox<String> roleComboBox;

    public Login_Interface() {
        initComponents();
    }

    private void initComponents() {
        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("LOGIN");
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

        companyNameLabel = new JLabel("Store Name", SwingConstants.CENTER);
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
        titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 102, 102));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        // Email
        emailLabel = new JLabel("User ID");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        nameField = new JTextField(30);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 2;
        panel.add(nameField, gbc);

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

        // Role
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = 5;
        panel.add(roleLabel, gbc);

        String[] roles = { "Cashier", "Sales Assistant", "Store Manager" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 6;
        panel.add(roleComboBox, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginButton.setBackground(new Color(0, 102, 102));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this); // Add ActionListener
        gbc.gridy = 7;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(loginButton, gbc);
        
        // Sign Up Button
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        signUpButton.setBackground(new Color(0, 102, 102));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(this); // Add ActionListener
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 8; // Corrected grid position
        panel.add(signUpButton, gbc);

        return panel;
    }

    private boolean authenticate(String username, String password, String role) {
        // Authentication logic (for simplicity, assume a fixed username/password)
        return username.equals("admin") && password.equals("password");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String username = nameField.getText();
            String password = new String(passwordField.getPassword());
            String role = roleComboBox != null ? roleComboBox.getSelectedItem().toString() : "";

            if (authenticate(username, password, role)) {
                setVisible(false);
                new Main_Dashboard(role).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
            }
        }
        if (ae.getSource() == signUpButton) {
            openSignUpForm();
        }
    }

    private void openSignUpForm() {
        setVisible(false);
        new SignUp_Interface().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login_Interface().setVisible(true));
    }
}
