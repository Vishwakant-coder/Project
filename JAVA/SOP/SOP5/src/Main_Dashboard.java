import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main_Dashboard extends JFrame implements ActionListener {

    private JButton inventoryButton, salesRecordsButton, hrButton, supplierButton, backButton, helpButton;
    private String userRole;

    public Main_Dashboard(String role) {
        this.userRole = role;
        setTitle("Dashboard");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Background Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(300, 480));
        imagePanel.setBackground(new Color(0, 0, 102));

        // Load background image
        JLabel bgLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon("cart.png"); // Update the path to your image
        Image bgImage = bgIcon.getImage().getScaledInstance(300, 480, Image.SCALE_SMOOTH);
        bgLabel.setIcon(new ImageIcon(bgImage));
        imagePanel.add(bgLabel);

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Main Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 0, 153));
        contentPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        // Button configuration
        JButton[] buttons = new JButton[] {
            inventoryButton = new JButton("Inventory Management"),
            salesRecordsButton = new JButton("Sales Records"),
            hrButton = new JButton("Human Resources"),
            supplierButton = new JButton("Supplier Communication"),
            helpButton = new JButton("Help"), // Added Help Button
            backButton = new JButton("Back")
        };

        for (JButton button : buttons) {
            styleButton(button);
            contentPanel.add(button, gbc);
            gbc.gridy++;
        }

        // Adding action listeners
        backButton.addActionListener(this);
        inventoryButton.addActionListener(this);
        salesRecordsButton.addActionListener(this);
        hrButton.addActionListener(this);
        supplierButton.addActionListener(this);
        helpButton.addActionListener(this); // Added action listener for Help button

        // Adjust button visibility based on user role
        adjustButtonVisibility();

        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(250, 40)); // Consistent button size
    }

    private void adjustButtonVisibility() {
        if (userRole.equals("Cashier") || userRole.equals("Sales Assistant")) {
            hrButton.setEnabled(false);
            supplierButton.setEnabled(false);
        } else if (userRole.equals("Store Manager") || userRole.equals("HR Manager")) {
            // Managers have access to all features
        } else {
            // Default case or error handling
            JOptionPane.showMessageDialog(null, "Unknown role: " + userRole);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            this.dispose();
            new Login_Form().setVisible(true);
        } else if (ae.getSource() == inventoryButton) {
            this.dispose();
            new Inventory_Form(userRole).setVisible(true);
        } else if (ae.getSource() == salesRecordsButton) {
            this.dispose();
            new Sales_Record_Form(userRole).setVisible(true);
        } else if (ae.getSource() == hrButton) {
            this.dispose();
            new Human_Resource_Form(userRole).setVisible(true);
        } else if (ae.getSource() == supplierButton) {
            this.dispose();
            new Supplier_Communication_Form(userRole).setVisible(true);
        } else if (ae.getSource() == helpButton) { // Handle Help Button
            this.dispose();
            new HelpPage().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_Dashboard("Store Manager").setVisible(true));
    }
}
