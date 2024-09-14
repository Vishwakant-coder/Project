import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    private JButton inventoryButton, salesRecordsButton, hrButton, supplierButton;
    private JButton receiptButton, helpButton, backButton;
    private String userRole;

    public Dashboard(String role) {
        this.userRole = role;
        setTitle("BF POS System - Main Dashboard");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create and configure the left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(204, 204, 255)); // Light blue color
        leftPanel.setPreferredSize(new Dimension(200, 480));
        leftPanel.setLayout(new BorderLayout());

        // Create a JLabel with the scaled image for the left panel
        ImageIcon left = new ImageIcon(ClassLoader.getSystemResource("left.jpg"));
        Image scaledImage1 = left.getImage().getScaledInstance(200, 480, Image.SCALE_SMOOTH);
        ImageIcon scaled1 = new ImageIcon(scaledImage1);
        JLabel leftImageLabel = new JLabel(scaled1);
        leftPanel.add(leftImageLabel, BorderLayout.CENTER);

        // Create and configure the right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(255, 204, 204)); // Light red color
        rightPanel.setPreferredSize(new Dimension(200, 480));
        rightPanel.setLayout(new BorderLayout());

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
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titleLabel.setForeground(new Color(0, 0, 153));
        centerPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;

        inventoryButton = new JButton("Inventory Management");
        inventoryButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        inventoryButton.setForeground(Color.WHITE);
        inventoryButton.setBackground(new Color(0, 102, 204));
        inventoryButton.addActionListener(this);
        centerPanel.add(inventoryButton, gbc);

        gbc.gridx++;
        salesRecordsButton = new JButton("Sales Records");
        salesRecordsButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        salesRecordsButton.setForeground(Color.WHITE);
        salesRecordsButton.setBackground(new Color(0, 102, 204));
        salesRecordsButton.addActionListener(this);
        centerPanel.add(salesRecordsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        hrButton = new JButton("Human Resources");
        hrButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        hrButton.setForeground(Color.WHITE);
        hrButton.setBackground(new Color(0, 102, 204));
        hrButton.addActionListener(this);
        centerPanel.add(hrButton, gbc);

        gbc.gridx++;
        supplierButton = new JButton("Supplier Communication");
        supplierButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        supplierButton.setForeground(Color.WHITE);
        supplierButton.setBackground(new Color(0, 102, 204));
        supplierButton.addActionListener(this);
        centerPanel.add(supplierButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        receiptButton = new JButton("Receipt Page");
        receiptButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        receiptButton.setForeground(Color.WHITE);
        receiptButton.setBackground(new Color(0, 102, 204));
        receiptButton.addActionListener(this);
        centerPanel.add(receiptButton, gbc);

        gbc.gridx++;
        helpButton = new JButton("Help Page");
        helpButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        helpButton.setForeground(Color.WHITE);
        helpButton.setBackground(new Color(0, 102, 204));
        helpButton.addActionListener(this);
        centerPanel.add(helpButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 153, 76));
        backButton.addActionListener(this);
        centerPanel.add(backButton, gbc);

        // Set button access based on role
        if (role.equals("Cashier") || role.equals("Sales Assistant")) {
            hrButton.setEnabled(false);
            supplierButton.setEnabled(false);
        } else if (role.equals("Store Manager") || role.equals("HR Manager")) {
            // Managers have access to all features
        } else {
            // Default case or error handling
            JOptionPane.showMessageDialog(this, "Unknown role: " + role);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new User_Login().setVisible(true);
        } else if (ae.getSource() == inventoryButton) {
            setVisible(false);
            new Inventory_Page(userRole).setVisible(true);
        } else if (ae.getSource() == salesRecordsButton) {
            setVisible(false);
            new Sales_Record_Page(userRole).setVisible(true);
        } else if (ae.getSource() == hrButton) {
            setVisible(false);
            new HR(userRole).setVisible(true);
        } else if (ae.getSource() == supplierButton) {
            setVisible(false);
            new Supplier_Page(userRole).setVisible(true);
        } 
        else if (ae.getSource() == receiptButton) {
            setVisible(false);
            new Receipt_Page().setVisible(true); 
        } else if (ae.getSource() == helpButton) {
            setVisible(false);
            new Help_Page().setVisible(true); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard("Store Manager").setVisible(true));
    }
}
