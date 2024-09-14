import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Inventory_Page extends JFrame {
    private JTable inventoryTable;
    private DefaultTableModel inventoryModel;
    private String userRole;

    public Inventory_Page(String role) {
        this.userRole = role;
        setTitle("Inventory Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create table model and table
        inventoryModel = new DefaultTableModel(new String[]{"Product Name", "Category", "Price", "Stock"}, 0);
        inventoryTable = new JTable(inventoryModel);
        customizeTable();

        // Add sample data
        addSampleData();

        // Set up table scroll pane
        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Add Product Button
        JButton addProductButton = new JButton("Add Product");
        addProductButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addProductButton.setForeground(Color.WHITE);
        addProductButton.setBackground(new Color(0, 102, 204));
        addProductButton.setFocusPainted(false);
        addProductButton.setBorderPainted(false);
        buttonPanel.add(addProductButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(204, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        buttonPanel.add(backButton);

        // Add panel to the frame
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
                new Dashboard(userRole).setVisible(true);
            }
        });
    }

    private void addSampleData() {
        inventoryModel.addRow(new Object[]{"Smartphone", "Electronics", "$299", "25"});
        inventoryModel.addRow(new Object[]{"Laptop", "Electronics", "$799", "15"});
        inventoryModel.addRow(new Object[]{"Headphones", "Accessories", "$59", "50"});
        inventoryModel.addRow(new Object[]{"Backpack", "Accessories", "$45", "40"});
        inventoryModel.addRow(new Object[]{"Blender", "Home Appliances", "$89", "20"});
        inventoryModel.addRow(new Object[]{"Digital Camera", "Electronics", "$499", "10"});
        inventoryModel.addRow(new Object[]{"Smartwatch", "Electronics", "$199", "30"});
        inventoryModel.addRow(new Object[]{"Air Fryer", "Home Appliances", "$129", "18"});
        inventoryModel.addRow(new Object[]{"Office Chair", "Furniture", "$149", "22"});
        inventoryModel.addRow(new Object[]{"Dining Table", "Furniture", "$399", "12"});
        inventoryModel.addRow(new Object[]{"Microwave Oven", "Home Appliances", "$79", "25"});
        inventoryModel.addRow(new Object[]{"Electric Kettle", "Home Appliances", "$39", "35"});
        inventoryModel.addRow(new Object[]{"Yoga Mat", "Sports", "$29", "60"});
        inventoryModel.addRow(new Object[]{"Treadmill", "Sports", "$899", "8"});
        inventoryModel.addRow(new Object[]{"Bookshelf", "Furniture", "$89", "28"});
    }

    private void customizeTable() {
        // Customize column widths
        inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(200); // Product Name
        inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Category
        inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Price
        inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Stock

        // Center-align text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        inventoryTable.setDefaultRenderer(Object.class, centerRenderer);

        // Customize table header
        JTableHeader header = inventoryTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);

        // Add borders and alternate row colors
        inventoryTable.setShowGrid(true);
        inventoryTable.setGridColor(Color.LIGHT_GRAY);
        inventoryTable.setIntercellSpacing(new Dimension(1, 1));
        inventoryTable.setRowHeight(30);

        // Set alternating row colors
        inventoryTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final Color oddColor = new Color(245, 245, 245);
            private final Color evenColor = Color.WHITE;
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? evenColor : oddColor);
                return c;
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventory_Page("Store Manager").setVisible(true));
    }
}
