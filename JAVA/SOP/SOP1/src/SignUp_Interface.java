

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUp_Interface extends JFrame implements ActionListener {

    private final JButton Register;
    private final JButton Clear;
    private final JButton Back;
    private final JTextField IDTextField;
    private final JPasswordField PassTextField;
    private final JComboBox<String> roleComboBox;

    public SignUp_Interface() {
        setTitle("BF POS System - Sign Up");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        // Placing image to frame 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel lable = new JLabel(i3);
        lable.setBounds(50, 10, 150, 100);
        add(lable);

        // Placing image to frame 
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("cart.jpg"));
        Image ii2 = ii1.getImage().getScaledInstance(1000, 600, Image.SCALE_AREA_AVERAGING);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel bg = new JLabel(ii3);
        bg.setBounds(0, 0, 800, 480);
        add(bg);

        // Sign Up Message
        JLabel text = new JLabel("CREATE YOUR ACCOUNT");
        text.setFont(new Font("Osward", Font.BOLD, 27));
        text.setBounds(180, 40, 500, 40);
        text.setForeground(Color.WHITE);
        bg.add(text);

        // User ID
        JLabel nameField = new JLabel("User ID :");
        nameField.setFont(new Font("Raleway", Font.BOLD, 27));
        nameField.setBounds(80, 180, 150, 30);
        nameField.setForeground(Color.WHITE);
        bg.add(nameField);

        // TextField for user to enter User ID
        IDTextField = new JTextField();
        IDTextField.setBounds(235, 182, 400, 30);
        bg.add(IDTextField);

        // Password
        JLabel Pass = new JLabel("Password :");
        Pass.setFont(new Font("Raleway", Font.BOLD, 27));
        Pass.setBounds(80, 250, 300, 30);
        Pass.setForeground(Color.WHITE);
        bg.add(Pass);

        // TextField for user to enter Password
        PassTextField = new JPasswordField();
        PassTextField.setBounds(235, 252, 400, 30);
        bg.add(PassTextField);

        // Role
        JLabel roleLabel = new JLabel("Role :");
        roleLabel.setFont(new Font("Raleway", Font.BOLD, (27)));
        roleLabel.setBounds(80, 320, 150, 30);
        roleLabel.setForeground(Color.WHITE);
        bg.add(roleLabel);

        // ComboBox for user to select role
        String[] roles = { "Cashier", "Sales Assistant", "Store Manager" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(235, 322, 400, 30);
        bg.add(roleComboBox);

        // Creating Buttons Using JButton class of Swing
        Register = new JButton("REGISTER");
        Register.setBounds(255, 370, 150, 30);
        // To Catch event of Button (Click)
        Register.addActionListener(this);
        bg.add(Register);

        // Creating Buttons Using JButton class of Swing
        Clear = new JButton("CLEAR");
        Clear.setBounds(465, 370, 150, 30);
        // To Catch event of Button (Click)
        Clear.addActionListener(this);
        bg.add(Clear);

        // Creating Buttons Using JButton class of Swing
        Back = new JButton("BACK");
        Back.setBounds(360, 420, 150, 30);
        // To Catch event of Button (Click)
        Back.addActionListener(this);
        bg.add(Back);

        setSize(800, 510);
        setVisible(true);
        setLocation(350, 200);
    }

    @Override
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
