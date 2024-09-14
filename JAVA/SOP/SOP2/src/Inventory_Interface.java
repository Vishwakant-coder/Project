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
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // Right panel for branding/logos
        JPanel rightPanel = createRightPanel();
        mainPanel.add(rightPanel, BorderLayout.WEST);

        // Left panel for inventory table and buttons
        JPanel leftPanel = createLeftPanel();
        mainPanel.add(leftPanel, BorderLayout.CENTER);
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 102, 102));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel companyNameLabel = new JLabel("Store Name", SwingConstants.CENTER);
        companyNameLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 24));
        companyNameLabel.setForeground(Color.WHITE);

        JLabel copyrightLabel = new JLabel("© Store Name. All rights reserved.", SwingConstants.CENTER);
        copyrightLabel.setForeground(new Color(204, 204, 204));

        panel.add(Box.createVerticalStrut(20));
        panel.add(companyNameLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(copyrightLabel);

        return panel;
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Inventory Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 102));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table setup
        inventoryModel = new DefaultTableModel(new String[]{"Product Name", "Category", "Price", "Stock"}, 0);
        inventoryTable = new JTable(inventoryModel);
        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Sample data
        inventoryModel.addRow(new Object[]{"Red Dress", "Women", "$45", "15"});
        inventoryModel.addRow(new Object[]{"Leather Jacket", "Men", "$120", "10"});
        inventoryModel.addRow(new Object[]{"Sneakers", "Footwear", "$60", "25"});
        inventoryModel.addRow(new Object[]{"Handbag", "Accessories", "$80", "20"});
        inventoryModel.addRow(new Object[]{"Wool Scarf", "Accessories", "$25", "40"});

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Add Product Button
        JButton addProductButton = new JButton("Add Product");
        addProductButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addProductButton.setBackground(new Color(0, 102, 102));
        addProductButton.setForeground(Color.WHITE);
        buttonPanel.add(addProductButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setBackground(new Color(0, 102, 102));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

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

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventory_Interface("Store Manager").setVisible(true));
    }
}
