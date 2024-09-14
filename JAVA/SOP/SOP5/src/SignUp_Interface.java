import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SignUp_Interface extends JFrame implements ActionListener {
    private JButton registerButton, clearButton, backButton;
    private JTextField idField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public SignUp_Interface() {
        setTitle("Sign Up Form");
        setSize(800, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Section 1: Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(300, 470));
        imagePanel.setBackground(new Color(0, 0, 102));

        // Load the new image
        JLabel imageLabel = new JLabel();
        try {
            @SuppressWarnings("deprecation")
            URL imageUrl = new URL("https://plus.unsplash.com/premium_photo-1675660733745-cb52470518ba?q=80&w=1854&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
            Image image = ImageIO.read(imageUrl);
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(300, 400, Image.SCALE_SMOOTH));
            imageLabel.setIcon(imageIcon);
        } catch (IOException e) {
            imageLabel.setText("Image not found");
        }
        imagePanel.add(imageLabel);

        // Section 2: Input Fields and Button Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(255, 255, 255));  // White background for contrast

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("CREATE YOUR ACCOUNT");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 0, 153));
        inputPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        JLabel idLabel = new JLabel("User ID:");
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        idLabel.setForeground(new Color(0, 0, 102));
        inputPanel.add(idLabel, gbc);

        gbc.gridx++;
        idField = new JTextField();
        idField.setPreferredSize(new Dimension(200, 30));  // Width and height
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        inputPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(0, 0, 102));
        inputPanel.add(passwordLabel, gbc);

        gbc.gridx++;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));  // Width and height
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        roleLabel.setForeground(new Color(0, 0, 102));
        inputPanel.add(roleLabel, gbc);

        gbc.gridx++;
        String[] roles = {"Cashier", "Sales Manager", "Store Manager"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        roleComboBox.setPreferredSize(new Dimension(200, 30));  // Set size for consistency
        inputPanel.add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(this);
        buttonPanel.add(registerButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        inputPanel.add(buttonPanel, gbc);

        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerButton) {
            String username = idField.getText();
            String password = new String(passwordField.getPassword());
            String role = roleComboBox.getSelectedItem().toString();

            if (registerUser(username, password, role)) {
                JOptionPane.showMessageDialog(null, "Registration Successful");
                this.dispose();
                new Login_Form().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        } else if (ae.getSource() == clearButton) {
            idField.setText("");
            passwordField.setText("");
            roleComboBox.setSelectedIndex(0);
        } else if (ae.getSource() == backButton) {
            this.dispose();
            new Login_Form().setVisible(true);
        }
    }

    private boolean registerUser(String username, String password, String role) {
        // Registration logic (for simplicity, assume it succeeds)
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignUp_Interface frame = new SignUp_Interface();
            frame.setVisible(true);
        });
    }
}
