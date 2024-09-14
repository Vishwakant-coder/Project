import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Dashboard extends JFrame implements ActionListener {

    private final RoundedButton inventoryButton;
    private final RoundedButton salesRecordsButton;
    private final RoundedButton receipButton;
    private final RoundedButton hrButton;
    private final RoundedButton supplierButton;
    private final RoundedButton backButton;
    private final String userRole;

    public Main_Dashboard(String role) {
        this.userRole = role;
        setTitle("BF POS System - Main Dashboard");
        setLayout(null);
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        // Background Image
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("cart.jpg"));
        Image ii2 = ii1.getImage().getScaledInstance(800, 480, Image.SCALE_AREA_AVERAGING);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel bg = new JLabel(ii3);
        bg.setBounds(0, 0, 800, 480);
        gradientPanel.add(bg);

        // Set Buttons
        inventoryButton = new RoundedButton("Inventory Management", 20);
        salesRecordsButton = new RoundedButton("Sales Records", 20);
        hrButton = new RoundedButton("Human Resources", 20);
        supplierButton = new RoundedButton("Supplier Communication", 20);
        backButton = new RoundedButton("BACK", 20);
        receipButton = new RoundedButton("Receipt Print", 20);

        // Button positions and sizes (updated for consistent spacing)
        inventoryButton.setBounds(20, 100, 220, 40);
        salesRecordsButton.setBounds(20, 150, 220, 40);
        hrButton.setBounds(20, 200, 220, 40);
        supplierButton.setBounds(20, 250, 220, 40);
        receipButton.setBounds(20, 300, 220, 40);
        backButton.setBounds(20, 350, 220, 40);

        // Add Action Listeners
        backButton.addActionListener(this);
        inventoryButton.addActionListener(this);
        salesRecordsButton.addActionListener(this);
        hrButton.addActionListener(this);
        supplierButton.addActionListener(this);
        receipButton.addActionListener(this);

        // Add Buttons to the Background Panel
        bg.add(inventoryButton);
        bg.add(salesRecordsButton);
        bg.add(hrButton);
        bg.add(supplierButton);
        bg.add(receipButton);
        bg.add(backButton);

        // Role-based Access Control
        if (role.equals("Cashier") || role.equals("Sales Assistant")) {
            hrButton.setEnabled(false);
            supplierButton.setEnabled(false);
        } else if (role.equals("Store Manager") || role.equals("HR Manager")) {
            // Managers have access to all features
        } else {
            // Default case or error handling
            JOptionPane.showMessageDialog(null, "Unknown role: " + role);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            this.dispose();
            new Login_Interface().setVisible(true);
        } else if (ae.getSource() == inventoryButton) {
            this.dispose();
            new Inventory_Interface(userRole).setVisible(true);
        } else if (ae.getSource() == salesRecordsButton) {
            this.dispose();
            new Sales_Record(userRole).setVisible(true);
        } else if (ae.getSource() == hrButton) {
            this.dispose();
            new Human_Resource(userRole).setVisible(true);
        } else if (ae.getSource() == supplierButton) {
            this.dispose();
            new Supplier_Communication(userRole).setVisible(true);
        } else if (ae.getSource() == receipButton) {
            this.dispose();
            new ReceiptPage().setVisible(true);
        }
    }

    public static void main(String[] args) {
        // For testing purposes, you can pass any role like "Cashier", "Sales Assistant", or "Store Manager"
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main_Dashboard("Store Manager").setVisible(true);
            }
        });
    }
}
