

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Inventory_Interface extends JFrame {
    private final JTable inventoryTable;
    private DefaultTableModel inventoryModel;
    private String userRole;

    public Inventory_Interface(String role) {
        this.userRole = role;
        setTitle("BF POS System - Inventory Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table columns: Product Name, Category, Price, Stock
        inventoryModel = new DefaultTableModel(new String[]{"Product Name", "Category", "Price", "Stock"}, 0);
        inventoryTable = new JTable(inventoryModel);

        // Sample data
        inventoryModel.addRow(new Object[]{"T-Shirt", "Clothes", "$10", "50"});
        inventoryModel.addRow(new Object[]{"Jeans", "Clothes", "$20", "30"});
        inventoryModel.addRow(new Object[]{"Shirt", "Clothes", "$20", "30"});
        inventoryModel.addRow(new Object[]{"Black T-Shirt", "Clothes", "$20", "30"});
        inventoryModel.addRow(new Object[]{"Polo", "Clothes", "$20", "30"});

        // Add Product Button
        JButton addProductButton = new JButton("Add Product");
        // Back Button
        JButton backButton = new JButton("Back");

        // Set up layout
        setLayout(new BorderLayout());
        add(new JScrollPane(inventoryTable), BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addProductButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for add product button
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Role-based access control for adding products
                if (userRole.equals("Store Manager")) {
                    String name = JOptionPane.showInputDialog("Product Name:");
                    String category = JOptionPane.showInputDialog("Category:");
                    String price = JOptionPane.showInputDialog("Price:");
                    String stock = JOptionPane.showInputDialog("Stock:");

                    if (name != null && category != null && price != null && stock != null) {
                        inventoryModel.addRow(new Object[]{name, category, price, stock});
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have permission to add products.");
                }
            }
        });

        // Action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main_Dashboard(role).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventory_Interface("Store Manager").setVisible(true));
    }
}
