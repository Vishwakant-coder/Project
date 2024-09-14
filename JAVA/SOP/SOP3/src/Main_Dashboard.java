import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main_Dashboard extends JFrame implements ActionListener {

    // Creating Buttons
    private JButton inventoryButton, salesRecordsButton, hrButton, supplierButton, backButton;
    private String userRole;

    public Main_Dashboard(String role) {
        this.userRole = role;
        setTitle("Main Dashboard");
        setLayout(new BorderLayout());
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;    

        // Background Image
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/mall.jpg"));
        if (backgroundIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Image not found or could not be loaded.");
        }
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(800, 480, Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(backgroundImage);
        JLabel bgLabel = new JLabel(backgroundIcon);
        add(bgLabel, BorderLayout.CENTER);
        bgLabel.setLayout(new GridBagLayout());

        // Set Layout for Buttons
        inventoryButton = new JButton("Inventory Management");
        salesRecordsButton = new JButton("Sales Records");
        hrButton = new JButton("Human Resources");
        supplierButton = new JButton("Supplier Communication");
        backButton = new JButton("BACK");

        // Add Action Listeners
        inventoryButton.addActionListener(this);
        salesRecordsButton.addActionListener(this);
        hrButton.addActionListener(this);
        supplierButton.addActionListener(this);
        backButton.addActionListener(this);

        // Add Buttons to the Layout
        gbc.gridy = 0;
        bgLabel.add(inventoryButton, gbc);

        gbc.gridy = 1;
        bgLabel.add(salesRecordsButton, gbc);

        gbc.gridy = 2;
        bgLabel.add(hrButton, gbc);

        gbc.gridy = 3;
        bgLabel.add(supplierButton, gbc);

        gbc.gridy = 4;
        bgLabel.add(backButton, gbc);

        // Role-Based Access Control
        if (role.equals("Cashier") || role.equals("Sales Assistant")) {
            hrButton.setEnabled(false);
            supplierButton.setEnabled(false);
        } else if (role.equals("Store Manager") || role.equals("HR Manager")) {
            // Managers have access to all features
        } else {
            JOptionPane.showMessageDialog(this, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == backButton) {
            this.dispose();
            new Login_Page().setVisible(true);
        } else if (source == inventoryButton) {
            this.dispose();
            new Inventory_Management(userRole).setVisible(true);
        } else if (source == salesRecordsButton) {
            this.dispose();
            new Sales_Record(userRole).setVisible(true);
        } else if (source == hrButton) {
            this.dispose();
            new Human_Resource(userRole).setVisible(true);
        } else if (source == supplierButton) {
            this.dispose();
            new Supplier_Communication(userRole).setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_Dashboard("Store Manager").setVisible(true));
    }
}
