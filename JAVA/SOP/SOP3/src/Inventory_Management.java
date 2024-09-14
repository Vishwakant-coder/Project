import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Inventory_Management extends JFrame {
    private JTable inventoryTable;
    private DefaultTableModel inventoryModel;
    private String userRole;

    public Inventory_Management(String role) {
        this.userRole = role;
        setTitle("Inventory Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        // Set background color for the main panel
        mainPanel.setBackground(new Color(245, 245, 245)); // Light Gray

        // Title label
        JLabel titleLabel = new JLabel("Inventory Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setOpaque(true); // Make sure background color is visible
        titleLabel.setBackground(new Color(70, 130, 180)); // Steel Blue
        titleLabel.setForeground(Color.WHITE); // White text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        mainPanel.add(titleLabel, gbc);

        // Table columns: Product Name, Category, Price, Stock
        inventoryModel = new DefaultTableModel(new String[]{"Product Name", "Category", "Price", "Stock"}, 0);
        inventoryTable = new JTable(inventoryModel);

        // Set background color for table header
        JTableHeader tableHeader = inventoryTable.getTableHeader();
        tableHeader.setBackground(new Color(70, 130, 180)); // Steel Blue
        tableHeader.setForeground(Color.WHITE); // White text

        // Set background color for table cells
        inventoryTable.setBackground(Color.WHITE); // White background for table cells
        inventoryTable.setGridColor(Color.LIGHT_GRAY); // Grid color

        // Sample data
        inventoryModel.addRow(new Object[]{"Running Shoes", "Footwear", "$80", "25"});
        inventoryModel.addRow(new Object[]{"Winter Jacket", "Outerwear", "$120", "15"});
        inventoryModel.addRow(new Object[]{"Smartwatch", "Electronics", "$150", "40"});
        inventoryModel.addRow(new Object[]{"Leather Belt", "Accessories", "$25", "60"});
        inventoryModel.addRow(new Object[]{"Sunglasses", "Accessories", "$45", "30"});

        // Table scroll pane
        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(tableScrollPane, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(5, 5, 5, 5);
        buttonGbc.anchor = GridBagConstraints.CENTER;
        buttonGbc.fill = GridBagConstraints.NONE;

        // Set background color for button panel
        buttonPanel.setBackground(new Color(245, 245, 245)); // Light Gray

        // Add Product Button
        JButton addProductButton = new JButton("Add Product");
        addProductButton.setBackground(new Color(34, 139, 34)); // Forest Green
        addProductButton.setForeground(Color.WHITE);
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(addProductButton, buttonGbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(220, 20, 60)); // Crimson
        backButton.setForeground(Color.WHITE);
        buttonGbc.gridx = 1;
        buttonPanel.add(backButton, buttonGbc);

        // Add button panel to main panel
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
        mainPanel.add(buttonPanel, gbc);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

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
        SwingUtilities.invokeLater(() -> new Inventory_Management("Store Manager").setVisible(true));
    }
}
