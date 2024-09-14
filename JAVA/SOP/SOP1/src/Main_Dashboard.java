
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Dashboard extends JFrame implements ActionListener{

    // Creating Button
        JButton inventoryButton = new JButton("Inventory Management");
        JButton salesRecordsButton = new JButton("Sales Records");
        JButton hrButton = new JButton("Human Resources");
        JButton supplierButton = new JButton("Supplier Communication");
        JButton Back = new JButton("BACK");
        private final String userRole;
    public Main_Dashboard(String role) {

        this.userRole = role;
        setTitle("BF POS System - Main Dashboard");
        setLayout(null);
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

   
        // Placing image to frame 
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("cart.jpg"));
        Image ii2 = ii1.getImage().getScaledInstance(1000, 600, Image.SCALE_AREA_AVERAGING);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel bg = new JLabel(ii3);
        bg.setBounds(0, 0, 800, 480);
        add(bg);
        
        
        // Set layout
        inventoryButton.setBounds(10, 100, 200, 30);
        salesRecordsButton.setBounds(10, 150, 200, 30);
        hrButton.setBounds(10, 200, 200, 30);
        supplierButton.setBounds(10, 250, 200, 30);
        Back.setBounds(10, 300, 200, 30);

        // To Catch event of Button (Click)
        Back.addActionListener(this);
        inventoryButton.addActionListener(this);
        salesRecordsButton.addActionListener(this);
        hrButton.addActionListener(this);
        supplierButton.addActionListener(this);
        
        bg.add(Back);
        bg.add(inventoryButton);
        bg.add(salesRecordsButton);
        bg.add(hrButton);
        bg.add(supplierButton);

        if (role.equals("Cashier") || role.equals("Sales Assistant")) {
            hrButton.setEnabled(false);
            supplierButton.setEnabled(false);
        } else if (role.equals("Store Manager")) {
            // Managers have access to all features
        }  else if (role.equals("HR Manager")) {
            // Managers have access to all features
        } else {
            // Default case or error handling
            JOptionPane.showMessageDialog(null, "Unknown role: " + role);
        }
    }

        @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == Back) {
            this.dispose();
            new Login_Interface().setVisible(true);
        } else if (ae.getSource() == inventoryButton) {
            this.dispose();
            new Inventory_Interface(userRole).setVisible(true);
        } else if (ae.getSource() == salesRecordsButton) {
            this.dispose();
            new Sales_Record_Interface(userRole).setVisible(true);
        }  else if (ae.getSource() == hrButton) {
            this.dispose();
            new Human_Resource_Interface(userRole).setVisible(true);
        }  else if (ae.getSource() == supplierButton) {
            this.dispose();
            new Supplier_Communication_Interface(userRole).setVisible(true);
        } 
    }
    public static void main(String[] args) {
        // For testing purposes, you can pass any role like "Cashier", "Sales Assistant", or "Store Manager"
        SwingUtilities.invokeLater(() -> {
            new Main_Dashboard("Store Manager").setVisible(true);
        });
    }
}
