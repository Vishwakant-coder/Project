import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Inventory_Interface extends JFrame {
    private JTable inventoryTable;
    private DefaultTableModel inventoryModel;
    private String userRole;

    public Inventory_Interface(String role) {
        this.userRole = role;
        setTitle("Inventory Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.BLACK);
        layeredPane.setOpaque(true);
        setContentPane(layeredPane);
        layeredPane.setLayout(new BorderLayout());

        // Create the table model and table
        inventoryModel = new DefaultTableModel(new String[]{"Product ID", "Product Name", "Category", "Quantity", "Price"}, 0);
        inventoryTable = new JTable(inventoryModel);
        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        tableScrollPane.setBounds(5, 10, 780, 380);  // Adjust bounds to match the size in the Human_Resource_Interface

        // Set table background and foreground colors
        inventoryTable.setBackground(Color.WHITE);
        inventoryTable.setForeground(Color.BLACK);
        inventoryTable.setGridColor(Color.GRAY);

        // Add sample data
        addSampleData();

        // Create buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        JButton addProductButton = new JButton("Add Product");
        addProductButton.setBackground(Color.DARK_GRAY);
        addProductButton.setForeground(Color.WHITE);
        addProductButton.addActionListener(new AddProductActionListener());
        
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new BackButtonActionListener());
        
        buttonPanel.add(addProductButton);
        buttonPanel.add(backButton);
        
        layeredPane.add(tableScrollPane, BorderLayout.CENTER);
        layeredPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addSampleData() {
        inventoryModel.addRow(new Object[]{"P101", "Shirt", "Apparel", "50", "$20.00"});
        inventoryModel.addRow(new Object[]{"P102", "Jeans", "Apparel", "30", "$40.00"});
        inventoryModel.addRow(new Object[]{"P103", "Sneakers", "Footwear", "20", "$60.00"});
        inventoryModel.addRow(new Object[]{"P104", "Hat", "Accessories", "15", "$15.00"});
        inventoryModel.addRow(new Object[]{"P105", "Jacket", "Apparel", "10", "$80.00"});
        inventoryModel.addRow(new Object[]{"P106", "Sunglasses", "Accessories", "25", "$50.00"});
        inventoryModel.addRow(new Object[]{"P107", "Backpack", "Accessories", "12", "$45.00"});
        inventoryModel.addRow(new Object[]{"P108", "Scarf", "Accessories", "22", "$25.00"});
        inventoryModel.addRow(new Object[]{"P109", "Gloves", "Accessories", "18", "$20.00"});
        inventoryModel.addRow(new Object[]{"P110", "Watch", "Accessories", "8", "$100.00"});
    }

    private class AddProductActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (userRole.equals("Store Manager")) {
                JTextField productIDField = new JTextField();
                JTextField productNameField = new JTextField();
                JTextField categoryField = new JTextField();
                JTextField quantityField = new JTextField();
                JTextField priceField = new JTextField();

                Object[] fields = {
                    "Product ID:", productIDField,
                    "Product Name:", productNameField,
                    "Category:", categoryField,
                    "Quantity:", quantityField,
                    "Price:", priceField
                };

                int option = JOptionPane.showConfirmDialog(null, fields, "Add Product", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String productID = productIDField.getText();
                    String productName = productNameField.getText();
                    String category = categoryField.getText();
                    String quantity = quantityField.getText();
                    String price = priceField.getText();

                    if (!productID.isEmpty() && !productName.isEmpty() && !category.isEmpty() && !quantity.isEmpty() && !price.isEmpty()) {
                        try {
                            Integer.parseInt(quantity);
                            Double.parseDouble(price.replace("$", "").replace(",", ""));
                            inventoryModel.addRow(new Object[]{productID, productName, category, quantity, price});
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Please enter valid quantity and price values.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "You do not have permission to add products.", "Access Denied", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new Main_Dashboard(userRole).setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventory_Interface("Store Manager").setVisible(true));
    }
}
