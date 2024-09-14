import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Inventory_Form extends JFrame {
    private JTable inventoryTable;
    private DefaultTableModel inventoryModel;
    private String userRole;

    public Inventory_Form(String role) {
        this.userRole = role;
        setTitle("Inventory Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // Table columns: Product Name, Category, Price, Stock
        inventoryModel = new DefaultTableModel(new String[]{"Product Name", "Category", "Price", "Stock"}, 0);
        inventoryTable = new JTable(inventoryModel);
        inventoryTable.setFillsViewportHeight(true);
        inventoryTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inventoryTable.setRowHeight(25);

        // Sample data
        inventoryModel.addRow(new Object[]{"Leather Jacket", "Outerwear", "$150", "20"});
        inventoryModel.addRow(new Object[]{"Running Shoes", "Footwear", "$85", "50"});
        inventoryModel.addRow(new Object[]{"Yoga Pants", "Activewear", "$40", "35"});
        inventoryModel.addRow(new Object[]{"Sun Hat", "Accessories", "$25", "60"});
        inventoryModel.addRow(new Object[]{"Denim Shorts", "Clothes", "$30", "40"});
        inventoryModel.addRow(new Object[]{"Wool Sweater", "Clothes", "$60", "25"});
        inventoryModel.addRow(new Object[]{"Formal Shirt", "Clothes", "$45", "50"});
        inventoryModel.addRow(new Object[]{"Sports Watch", "Accessories", "$100", "15"});
        inventoryModel.addRow(new Object[]{"Rain Boots", "Footwear", "$55", "30"});
        inventoryModel.addRow(new Object[]{"Casual Trousers", "Clothes", "$35", "45"});

        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        tableScrollPane.setBounds(5, 10, 780, 400);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Add Product Button
        JButton addProductButton = new JButton("Add Product");
        addProductButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addProductButton.setPreferredSize(new Dimension(150, 30));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(150, 30));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.add(addProductButton);
        buttonPanel.add(backButton);
        buttonPanel.setBounds(0, 410, 800, 50);

        // Add components to the layered pane
        layeredPane.add(tableScrollPane, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

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
                new Main_Dashboard(userRole).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventory_Form("Store Manager").setVisible(true));
    }
}
