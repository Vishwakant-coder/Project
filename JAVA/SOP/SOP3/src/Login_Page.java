import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login_Page extends JFrame implements ActionListener {
    private JButton signInButton, signUpButton, clearButton;
    private JTextField idTextField;
    private JPasswordField passwordTextField;
    private JComboBox<String> roleComboBox;

    public Login_Page() {
        setTitle("Login");
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Panel for background image and login form
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/mall.jpg"));
        if (backgroundImage.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Image not found or could not be loaded.");
        }

        Image img = backgroundImage.getImage().getScaledInstance(800, 480, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(img);
        JLabel bgLabel = new JLabel(backgroundImage);
        mainPanel.add(bgLabel);

        // Login form panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false); // Make it transparent to show background image
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Welcome Message with Border
        JLabel welcomeLabel = new JLabel("Welcome to the Store");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(welcomeLabel, gbc);

        // ID Number
        JLabel idLabel = new JLabel("User ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        idLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(idLabel, gbc);

        idTextField = new JTextField(20);
        gbc.gridx = 1;
        loginPanel.add(idTextField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridx = 0;
        loginPanel.add(passwordLabel, gbc);

        passwordTextField = new JPasswordField(20);
        gbc.gridx = 1;
        loginPanel.add(passwordTextField, gbc);

        // Role
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        roleLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.gridx = 0;
        loginPanel.add(roleLabel, gbc);

        String[] roles = { "Cashier", "Sales Assistant", "Store Manager" };
        roleComboBox = new JComboBox<>(roles);
        gbc.gridx = 1;
        loginPanel.add(roleComboBox, gbc);

        // Buttons
        signInButton = new JButton("Sign In");
        signInButton.setActionCommand("signIn");
        signInButton.addActionListener(this);
        gbc.gridy = 4;
        gbc.gridx = 0;
        loginPanel.add(signInButton, gbc);

        clearButton = new JButton("Clear");
        clearButton.setActionCommand("clear");
        clearButton.addActionListener(this);
        gbc.gridx = 1;
        loginPanel.add(clearButton, gbc);

        signUpButton = new JButton("Sign Up");
        signUpButton.setActionCommand("signUp");
        signUpButton.addActionListener(this);
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        loginPanel.add(signUpButton, gbc);

        // Add loginPanel to mainPanel
        bgLabel.setLayout(new GridBagLayout());
        GridBagConstraints bgConstraints = new GridBagConstraints();
        bgConstraints.gridx = 0;
        bgConstraints.gridy = 0;
        bgConstraints.anchor = GridBagConstraints.CENTER;
        bgLabel.add(loginPanel, bgConstraints);

        add(mainPanel, BorderLayout.CENTER);

        setSize(800, 480);
        setVisible(true);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private boolean authenticate(String username, String password, String role) {
        // Placeholder for actual authentication logic
        return username.equals("admin") && password.equals("password");
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.equals("signIn")) {
            String username = idTextField.getText();
            String password = new String(passwordTextField.getPassword());
            String role = roleComboBox.getSelectedItem().toString();

            if (authenticate(username, password, role)) {
                setVisible(false);
                new Main_Dashboard(role).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (command.equals("clear")) {
            idTextField.setText("");
            passwordTextField.setText("");
        } else if (command.equals("signUp")) {
            setVisible(false);
            new SignUp_Page().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login_Page().setVisible(true);
    }
}
