import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class User_SignUp extends JFrame implements ActionListener {

    private JButton registerButton, clearButton, backButton;
    private JTextField idTextField;
    private JPasswordField passTextField;
    private JComboBox<String> roleComboBox;

    public User_SignUp() {
        setTitle("Sign Up");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Create and configure the left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(204, 204, 255)); // Light blue color
        leftPanel.setPreferredSize(new Dimension(200, 480)); // Increased width
        leftPanel.setLayout(new BorderLayout()); // Use BorderLayout for better positioning

        // Create a JLabel with the scaled image for the left panel
        ImageIcon left = new ImageIcon(ClassLoader.getSystemResource("left.jpg"));
        Image scaledImage1 = left.getImage().getScaledInstance(200, 480, Image.SCALE_SMOOTH);
        ImageIcon scaled1 = new ImageIcon(scaledImage1);
        JLabel leftImageLabel = new JLabel(scaled1);
        leftPanel.add(leftImageLabel, BorderLayout.CENTER);

        // Create and configure the right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(255, 204, 204)); // Light red color
        rightPanel.setPreferredSize(new Dimension(200, 480)); // Increased width
        rightPanel.setLayout(new BorderLayout()); // Use BorderLayout for better positioning

        // Create a JLabel with the scaled image for the right panel
        ImageIcon right = new ImageIcon(ClassLoader.getSystemResource("cart_right.jpg"));
        Image scaledImage2 = right.getImage().getScaledInstance(200, 480, Image.SCALE_SMOOTH);
        ImageIcon scaled2 = new ImageIcon(scaledImage2);
        JLabel rightImageLabel = new JLabel(scaled2);
        rightPanel.add(rightImageLabel, BorderLayout.CENTER);

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

        JLabel titleLabel = new JLabel("Create Your Account");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titleLabel.setForeground(new Color(0, 0, 153));
        centerPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel idLabel = new JLabel("User ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        centerPanel.add(idLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_START;
        idTextField = new JTextField(20);
        centerPanel.add(idTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        centerPanel.add(passLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_START;
        passTextField = new JPasswordField(20);
        centerPanel.add(passTextField, gbc);

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

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(0, 102, 204));
        registerButton.addActionListener(this);
        buttonPanel.add(registerButton);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(new Color(0, 153, 76));
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 99, 0));
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        centerPanel.add(buttonPanel, gbc);
    }

    private boolean registerUser(String username, String password, String role) {
        // Registration logic (for simplicity, assume it succeeds)
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerButton) {
            String username = idTextField.getText();
            String password = new String(passTextField.getPassword());
            String role = roleComboBox.getSelectedItem().toString();

            if (registerUser(username, password, role)) {
                JOptionPane.showMessageDialog(this, "Registration Successful");
                // Redirect to login page
                setVisible(false);
                new User_Login().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed");
            }
        } else if (ae.getSource() == clearButton) {
            idTextField.setText("");
            passTextField.setText("");
            roleComboBox.setSelectedIndex(0);
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new User_Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new User_SignUp().setVisible(true));
    }
}
