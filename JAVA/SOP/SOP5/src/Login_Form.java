import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Login_Form extends JFrame implements ActionListener {

    private JTextField idField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton submitButton;
    private JButton signupButton;

    public Login_Form() {
        setTitle("Welcome to Grocery");
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

        JLabel titleLabel = new JLabel("LOGIN / SIGNUP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 0, 153));
        inputPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        JLabel idLabel = new JLabel("Enter Your ID:");
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

        JLabel passwordLabel = new JLabel("Enter Your Password:");
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

        JLabel roleLabel = new JLabel("Select Your Role:");
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

        // Submit Button
        submitButton = new JButton("Login");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(this);
        inputPanel.add(submitButton, gbc);

        // Signup Button
        gbc.gridx++;
        signupButton = new JButton("Signup");
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupButton.addActionListener(this);
        inputPanel.add(signupButton, gbc);

        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private boolean authenticate(String username, String password, String role) {
        // Dummy authentication logic (replace with actual logic)
        return username.equals("admin") && password.equals("password");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String username = idField.getText();
            String password = new String(passwordField.getPassword());
            String role = roleComboBox != null ? roleComboBox.getSelectedItem().toString() : "";

            if (authenticate(username, password, role)) {
                setVisible(false);
                new Main_Dashboard(role).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
            }
        } else if (ae.getSource() == signupButton) {
            setVisible(false);
            new SignUp_Interface().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login_Form frame = new Login_Form();
            frame.setVisible(true);
        });
    }
}
