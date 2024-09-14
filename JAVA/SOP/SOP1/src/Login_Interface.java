import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Login_Interface extends JFrame implements ActionListener {

    private final JButton SignIn;
    private final JButton SignUp;
    private final JButton Clear;
    private final JTextField IDTextField;
    private final JPasswordField PassTextField;
    private final JComboBox<String> roleComboBox;

    public Login_Interface() {
        setTitle("BF POS System - Login");
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
        Image ii2 = ii1.getImage().getScaledInstance(1000, 560, Image.SCALE_AREA_AVERAGING);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel bg = new JLabel(ii3);
        bg.setBounds(0, 0, 800, 480);
        add(bg);

        // Welcome Message
        JLabel text = new JLabel("WELCOME TO STORE");
        text.setFont(new Font("Osward", Font.BOLD, 33));
        text.setBounds(220, 40, 450, 40);
        text.setForeground(Color.WHITE);
        bg.add(text);

        // ID Number
        JLabel nameField = new JLabel("User ID :");
        nameField.setFont(new Font("Raleway", Font.BOLD, 30));
        nameField.setBounds(80, 150, 150, 30);
        nameField.setForeground(Color.WHITE);
        bg.add(nameField);

        // TextField For user to enter 
        IDTextField = new JTextField();
        IDTextField.setBounds(235, 152, 400, 30);
        bg.add(IDTextField);

        // Password
        JLabel Pass = new JLabel("Password :");
        Pass.setFont(new Font("Raleway", Font.BOLD, 30));
        Pass.setBounds(80, 200, 300, 30);
        Pass.setForeground(Color.WHITE);
        bg.add(Pass);

        // TextField For user to enter 
        PassTextField = new JPasswordField();
        PassTextField.setBounds(235, 202, 400, 30);
        bg.add(PassTextField);

        // Role
        JLabel roleLabel = new JLabel("Role :");
        roleLabel.setFont(new Font("Raleway", Font.BOLD, (27)));
        roleLabel.setBounds(80, 250, 150, 30);
        roleLabel.setForeground(Color.WHITE);
        bg.add(roleLabel);

        // ComboBox for user to select role
        String[] roles = { "Cashier", "Sales Assistant", "Store Manager" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(235, 252, 400, 30);
        bg.add(roleComboBox);

        // Creating Buttons Using JButton class of Swing
        SignIn = new JButton("SIGN IN");
        SignIn.setBounds(255, 300, 150, 30);
        // To Catch event of Button (Click)
        SignIn.addActionListener(this);
        bg.add(SignIn);

        // Creating Buttons Using JButton class of Swing
        Clear = new JButton("CLEAR");
        Clear.setBounds(465, 300, 150, 30);
        // To Catch event of Button (Click)
        Clear.addActionListener(this);
        bg.add(Clear);

        // Creating Buttons Using JButton class of Swing
        SignUp = new JButton("SIGN UP");
        SignUp.setBounds(357, 350, 150, 30);
        // To Catch event of Button (Click)
        SignUp.addActionListener(this);
        bg.add(SignUp);

        

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    private boolean authenticate(String username, String password, String role) {
        // Authentication logic (for simplicity, assume a fixed username/password)
        return username.equals("admin") && password.equals("password");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == SignIn) {
            String username = IDTextField.getText();
            String password = new String(PassTextField.getPassword());
            String role = roleComboBox != null ? roleComboBox.getSelectedItem().toString() : "";
            
            if (authenticate(username, password, role)) {
                setVisible(false);
                new Main_Dashboard(role).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
            }
        } else if (ae.getSource() == Clear) {
            IDTextField.setText("");
            PassTextField.setText("");
        } else if (ae.getSource() == SignUp) {
            setVisible(false);
            new SignUp_Interface().setVisible(true);
        }
    }


    public static void main(String[] args) {
        new Login_Interface().setVisible(true);
    }
}
