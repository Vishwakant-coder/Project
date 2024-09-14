import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUp_Interface extends JFrame implements ActionListener {
    private RoundedButton Register, Clear, Back;
    private RoundedTextField IDTextField;
    private RoundedPasswordField PassTextField;
    private RoundedComboBox<String> roleComboBox;

    public SignUp_Interface() {
        setTitle("Registation");
        setLayout(null);
        getContentPane().setBackground(new Color(60, 63, 65));

        // Background Gradient Panel
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(50, 50, 50), 0, getHeight(), new Color(25, 25, 25));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setBounds(0, 0, 800, 480);
        gradientPanel.setLayout(null);
        add(gradientPanel);

        // Placing image to frame 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("shop.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel lable = new JLabel(i3);
        lable.setBounds(50, 10, 150, 100);
        gradientPanel.add(lable);

        // Sign Up Message
        JLabel text = new JLabel("CREATE YOUR ACCOUNT");
        text.setFont(new Font("Osward", Font.BOLD, 27));
        text.setBounds(180, 40, 500, 40);
        text.setForeground(Color.WHITE);
        gradientPanel.add(text);

        // User ID
        JLabel nameField = new JLabel("User ID :");
        nameField.setFont(new Font("Raleway", Font.BOLD, 27));
        nameField.setBounds(80, 150, 150, 30);
        nameField.setForeground(Color.WHITE);
        gradientPanel.add(nameField);

        // Rounded TextField for User ID
        IDTextField = new RoundedTextField(20);
        IDTextField.setBounds(235, 150, 400, 30);
        gradientPanel.add(IDTextField);

        // Password
        JLabel Pass = new JLabel("Password :");
        Pass.setFont(new Font("Raleway", Font.BOLD, 27));
        Pass.setBounds(80, 200, 300, 30);
        Pass.setForeground(Color.WHITE);
        gradientPanel.add(Pass);

        // Rounded PasswordField for Password
        PassTextField = new RoundedPasswordField(20);
        PassTextField.setBounds(235, 200, 400, 30);
        gradientPanel.add(PassTextField);

        // Role
        JLabel roleLabel = new JLabel("Role :");
        roleLabel.setFont(new Font("Raleway", Font.BOLD, 27));
        roleLabel.setBounds(80, 250, 150, 30);
        roleLabel.setForeground(Color.WHITE);
        gradientPanel.add(roleLabel);

        // Custom Rounded JComboBox for roles
        String[] roles = { "Cashier", "Sales Assistant", "Store Manager" };
        roleComboBox = new RoundedComboBox<>(roles);
        roleComboBox.setBounds(235, 250, 400, 30);
        gradientPanel.add(roleComboBox);

        // Rounded Register Button
        Register = new RoundedButton("REGISTER", 20);
        Register.setBounds(255, 300, 150, 35);
        Register.addActionListener(this);
        gradientPanel.add(Register);

        // Rounded Clear Button
        Clear = new RoundedButton("CLEAR", 20);
        Clear.setBounds(465, 300, 150, 35);
        Clear.addActionListener(this);
        gradientPanel.add(Clear);

        // Rounded Back Button
        Back = new RoundedButton("BACK", 20);
        Back.setBackground(new Color(255, 69, 58)); // Optional: Different background color for Back button
        Back.setBounds(360, 350, 150, 35);
        Back.addActionListener(this);
        gradientPanel.add(Back);

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Register) {
            String username = IDTextField.getText();
            String password = new String(PassTextField.getPassword());
            String role = roleComboBox.getSelectedItem().toString();

            if (registerUser(username, password, role)) {
                JOptionPane.showMessageDialog(null, "Registration Successful");
                // Optionally redirect to login page
                this.dispose();
                new Login_Interface().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        } else if (ae.getSource() == Clear) {
            IDTextField.setText("");
            PassTextField.setText("");
            roleComboBox.setSelectedIndex(0);
        } else if (ae.getSource() == Back) {
            this.dispose();
            new Login_Interface().setVisible(true);
        }
    }

    private boolean registerUser(String username, String password, String role) {
        // Registration logic (for simplicity, assume it succeeds)
        return true;
    }

    public static void main(String[] args) {
        new SignUp_Interface().setVisible(true);
    }
}
