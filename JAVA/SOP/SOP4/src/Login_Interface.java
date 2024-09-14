import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login_Interface extends JFrame implements ActionListener {
    private RoundedButton SignIn, SignUp, Clear;
    private JTextField IDTextField;
    private JPasswordField PassTextField;
    private JComboBox<String> roleComboBox;

    public Login_Interface() {
        setTitle("Login");
        setLayout(null);
        getContentPane().setBackground(new Color(60, 63, 65));

        // Placing image to frame 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("shop.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel lable = new JLabel(i3);
        lable.setBounds(50, 10, 150, 100);
        add(lable);

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

        // Welcome Message
        JLabel text = new JLabel("WELCOME TO STORE");
        text.setFont(new Font("Osward", Font.BOLD, 33));
        text.setBounds(220, 40, 450, 40);
        text.setForeground(Color.WHITE);
        gradientPanel.add(text);

        // ID Number
        JLabel nameField = new JLabel("User ID :");
        nameField.setFont(new Font("Raleway", Font.BOLD, 28));
        nameField.setBounds(80, 150, 150, 30);
        nameField.setForeground(Color.WHITE);
        gradientPanel.add(nameField);

        // Rounded TextField For user to enter
        IDTextField = new RoundedTextField(20);
        IDTextField.setBounds(235, 152, 400, 30);
        gradientPanel.add(IDTextField);

        // Password
        JLabel Pass = new JLabel("Password :");
        Pass.setFont(new Font("Raleway", Font.BOLD, 28));
        Pass.setBounds(80, 200, 300, 30);
        Pass.setForeground(Color.WHITE);
        gradientPanel.add(Pass);

        // Rounded PasswordField For user to enter
        PassTextField = new RoundedPasswordField(20);
        PassTextField.setBounds(235, 202, 400, 30);
        gradientPanel.add(PassTextField);

        // Role
        JLabel roleLabel = new JLabel("Role :");
        roleLabel.setFont(new Font("Raleway", Font.BOLD, 28));
        roleLabel.setBounds(80, 250, 150, 30);
        roleLabel.setForeground(Color.WHITE);
        gradientPanel.add(roleLabel);

        // Custom Rounded JComboBox for roles
        roleComboBox = new RoundedComboBox<>(new String[]{"Cashier", "Sales Assistant", "Store Manager"});
        roleComboBox.setBounds(235, 252, 400, 30);
        gradientPanel.add(roleComboBox);

        // Rounded SignIn Button
        SignIn = new RoundedButton("SIGN IN", 20);
        SignIn.setBounds(255, 300, 150, 35);
        SignIn.addActionListener(this);
        gradientPanel.add(SignIn);

        // Rounded Clear Button
        Clear = new RoundedButton("CLEAR", 20);
        Clear.setBounds(465, 300, 150, 35);
        Clear.addActionListener(this);
        gradientPanel.add(Clear);

        // Rounded SignUp Button
        SignUp = new RoundedButton("SIGN UP", 20);
        SignUp.setBackground(new Color(34, 139, 34));  // Different background color
        SignUp.setBounds(357, 350, 150, 35);
        SignUp.addActionListener(this);
        gradientPanel.add(SignUp);

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    private boolean authenticate(String username, String password, String role) {
        return username.equals("admin") && password.equals("password");
    }

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

// Custom RoundedTextField class
class RoundedTextField extends JTextField {
    private int radius;

    public RoundedTextField(int radius) {
        super();
        this.radius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
}

// Custom RoundedPasswordField class
class RoundedPasswordField extends JPasswordField {
    private int radius;

    public RoundedPasswordField(int radius) {
        super();
        this.radius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
}

// Custom RoundedComboBox class
class RoundedComboBox<E> extends JComboBox<E> {
    private int radius;

    public RoundedComboBox(E[] items) {
        super(items);
        this.radius = 20; // Default radius for rounded corners
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setUI(new RoundedComboBoxUI());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
}

// Custom UI for the RoundedComboBox
class RoundedComboBoxUI extends javax.swing.plaf.basic.BasicComboBoxUI {
    @Override
    protected JButton createArrowButton() {
        JButton arrowButton = new JButton();
        arrowButton.setBorder(BorderFactory.createEmptyBorder());
        arrowButton.setBackground(Color.WHITE);
        return arrowButton;
    }
}

// Custom RoundedButton class
class RoundedButton extends JButton {
    private int radius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);  // Default text color
        setFont(new Font("Arial", Font.BOLD, 16));  // Default font
        setBackground(new Color(70, 130, 180));  // Default background color
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.dispose();
    }
}
