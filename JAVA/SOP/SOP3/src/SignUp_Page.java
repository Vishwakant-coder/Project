import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUp_Page extends JFrame implements ActionListener {
    private JButton registerButton, clearButton, backButton;
    private JTextField idTextField;
    private JPasswordField passTextField;
    private JComboBox<String> roleComboBox;

    public SignUp_Page() {
        setTitle("Sign Up");
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Panel for background image and sign up form
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

        // Sign up form panel
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridBagLayout());
        signUpPanel.setOpaque(false); // Make it transparent to show the background image
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create Account Message with Border
        JLabel signUpLabel = new JLabel("Create Your Account");
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signUpLabel.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        signUpPanel.add(signUpLabel, gbc);

        // User ID
        JLabel idLabel = new JLabel("User ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        idLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        signUpPanel.add(idLabel, gbc);

        idTextField = new JTextField(20);
        gbc.gridx = 1;
        signUpPanel.add(idTextField, gbc);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridx = 0;
        signUpPanel.add(passLabel, gbc);

        passTextField = new JPasswordField(20);
        gbc.gridx = 1;
        signUpPanel.add(passTextField, gbc);

        // Role
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        roleLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.gridx = 0;
        signUpPanel.add(roleLabel, gbc);

        String[] roles = {"Cashier", "Sales Assistant", "Store Manager"};
        roleComboBox = new JComboBox<>(roles);
        gbc.gridx = 1;
        signUpPanel.add(roleComboBox, gbc);

        // Buttons
        registerButton = new JButton("Register");
        registerButton.setActionCommand("register");
        registerButton.addActionListener(this);
        gbc.gridy = 4;
        gbc.gridx = 0;
        signUpPanel.add(registerButton, gbc);

        clearButton = new JButton("Clear");
        clearButton.setActionCommand("clear");
        clearButton.addActionListener(this);
        gbc.gridx = 1;
        signUpPanel.add(clearButton, gbc);

        backButton = new JButton("Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        signUpPanel.add(backButton, gbc);

        // Add signUpPanel to mainPanel
        bgLabel.setLayout(new GridBagLayout());
        GridBagConstraints bgConstraints = new GridBagConstraints();
        bgConstraints.gridx = 0;
        bgConstraints.gridy = 0;
        bgConstraints.anchor = GridBagConstraints.CENTER;
        bgLabel.add(signUpPanel, bgConstraints);

        add(mainPanel, BorderLayout.CENTER);

        setSize(800, 480);
        setVisible(true);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.equals("register")) {
            String username = idTextField.getText();
            String password = new String(passTextField.getPassword());
            String role = roleComboBox.getSelectedItem().toString();

            if (registerUser(username, password, role)) {
                JOptionPane.showMessageDialog(null, "Registration Successful");
                this.dispose();
                new Login_Page().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        } else if (command.equals("clear")) {
            idTextField.setText("");
            passTextField.setText("");
            roleComboBox.setSelectedIndex(0);
        } else if (command.equals("back")) {
            this.dispose();
            new Login_Page().setVisible(true);
        }
    }

    private boolean registerUser(String username, String password, String role) {
        // Registration logic (for simplicity, assume it succeeds)
        return true;
    }

    public static void main(String[] args) {
        new SignUp_Page().setVisible(true);
    }
}
