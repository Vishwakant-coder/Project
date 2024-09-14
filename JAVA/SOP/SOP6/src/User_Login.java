import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class User_Login extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, clearButton, signUpButton;
    private JComboBox<String> roleComboBox;

    public User_Login() {
        setTitle("Login");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Create and configure the left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(204, 204, 255)); // Light blue color
        leftPanel.setPreferredSize(new Dimension(200, 480)); // Increased width
        leftPanel.setLayout(null); // Use null layout for manual positioning

        // Create and configure the right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(255, 204, 204)); // Light red color
        rightPanel.setPreferredSize(new Dimension(200, 480)); // Increased width
        rightPanel.setLayout(null); // Use null layout for manual positioning

        // Create a JLabel with the scaled image for the left panel
        ImageIcon left = new ImageIcon(ClassLoader.getSystemResource("left.jpg"));
        Image scaledImage1 = left.getImage().getScaledInstance(200, 480, Image.SCALE_DEFAULT);
        ImageIcon scaled1 = new ImageIcon(scaledImage1);
        JLabel leftImageLabel = new JLabel(scaled1);
        leftImageLabel.setBounds(0, 0, 200, 480);
        leftPanel.add(leftImageLabel);

        // Create a JLabel with the scaled image for the right panel
        ImageIcon right = new ImageIcon(ClassLoader.getSystemResource("cart_right.jpg"));
        Image scaledImage2 = right.getImage().getScaledInstance(200, 480, Image.SCALE_DEFAULT);
        ImageIcon scaled2 = new ImageIcon(scaledImage2);
        JLabel rightImageLabel = new JLabel(scaled2);
        rightImageLabel.setBounds(0, 0, 200, 480);
        rightPanel.add(rightImageLabel);

        // Add left and right panels to the frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // Create and configure the center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible layout
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow components to resize horizontally
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("User Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titleLabel.setForeground(new Color(0, 0, 153));
        centerPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel usernameLabel = new JLabel("User ID:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        centerPanel.add(usernameLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_START;
        usernameField = new JTextField(20);
        centerPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        centerPanel.add(passwordLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(20);
        centerPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        centerPanel.add(roleLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_START;
        String[] roles = { "Cashier", "Sales Manager", "Store Manager" };
        roleComboBox = new JComboBox<>(roles);
        centerPanel.add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0)); // Horizontal layout for buttons

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(new Color(0, 153, 76));
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(0, 99, 0));
        signUpButton.addActionListener(this);
        buttonPanel.add(signUpButton);

        centerPanel.add(buttonPanel, gbc);
    }

    private boolean authenticate(String username, String password, String role) {
        // Placeholder authentication logic
        return username.equals("admin") && password.equals("password");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String role = roleComboBox != null ? roleComboBox.getSelectedItem().toString() : "";
            
            if (authenticate(username, password, role)) {
                setVisible(false);
                new Dashboard(role).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }
        } else if (ae.getSource() == clearButton) {
            usernameField.setText("");
            passwordField.setText("");
        } else if (ae.getSource() == signUpButton) {
            setVisible(false);
            new User_SignUp().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new User_Login().setVisible(true));
    }
}
