import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Supplier_Page extends JFrame {
    private JTable supplierTable;
    private DefaultTableModel supplierModel;
    private String userRole;

    public Supplier_Page(String role) {
        this.userRole = role;
        setTitle("Supplier Communication");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create table model and table
        supplierModel = new DefaultTableModel(new String[]{"Supplier ID", "Name", "Contact", "Item Data"}, 0);
        supplierTable = new JTable(supplierModel);
        customizeTable();

        // Add sample data
        addSampleData();

        // Set up table scroll pane
        JScrollPane tableScrollPane = new JScrollPane(supplierTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Form fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(Color.WHITE);

        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        JLabel itemDataLabel = new JLabel("Item Data:");
        JTextField supplierIDField = new JTextField(10);
        JTextField itemDataField = new JTextField(20);

        formPanel.add(supplierIDLabel);
        formPanel.add(supplierIDField);
        formPanel.add(itemDataLabel);
        formPanel.add(itemDataField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Update Item Data Button
        JButton updateItemDataButton = new JButton("Update Item Data");
        updateItemDataButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateItemDataButton.setForeground(Color.WHITE);
        updateItemDataButton.setBackground(new Color(0, 102, 204));
        updateItemDataButton.setFocusPainted(false);
        updateItemDataButton.setBorderPainted(false);
        buttonPanel.add(updateItemDataButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(204, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        buttonPanel.add(backButton);

        // Add components to the frame
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for update item data button
        updateItemDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Role-based access control for updating item data
                if (userRole.equals("Inventory Manager") || userRole.equals("Store Manager")) {
                    String supplierID = supplierIDField.getText();
                    String newItemData = itemDataField.getText();

                    if (supplierID != null && !supplierID.isEmpty() && newItemData != null && !newItemData.isEmpty()) {
                        for (int i = 0; i < supplierModel.getRowCount(); i++) {
                            if (supplierModel.getValueAt(i, 0).equals(supplierID)) {
                                supplierModel.setValueAt(newItemData, i, 3);
                                JOptionPane.showMessageDialog(null, "Item data updated successfully.");
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Supplier ID not found.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter both Supplier ID and Item Data.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have permission to update item data.");
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
        supplierModel.addRow(new Object[]{"S001", "Elite Supplies", "111-222-3333", "Office Supplies, Stationery"});
        supplierModel.addRow(new Object[]{"S002", "Global Traders", "444-555-6666", "Electronics, Gadgets"});
        supplierModel.addRow(new Object[]{"S003", "Urban Goods", "777-888-9999", "Furniture, Home Decor"});
        supplierModel.addRow(new Object[]{"S004", "Tech Solutions", "000-111-2222", "Computers, Accessories"});
        supplierModel.addRow(new Object[]{"S005", "Health Mart", "333-444-5555", "Medical Supplies, Equipment"});
        supplierModel.addRow(new Object[]{"S006", "Green Planet", "666-777-8888", "Organic Products, Eco-Friendly Items"});
        supplierModel.addRow(new Object[]{"S007", "Fashion Hub", "999-000-1111", "Clothing, Accessories"});
        supplierModel.addRow(new Object[]{"S008", "Auto Parts Inc.", "222-333-4444", "Automotive Parts, Tools"});
        supplierModel.addRow(new Object[]{"S009", "Book World", "555-666-7777", "Books, Magazines"});
        supplierModel.addRow(new Object[]{"S010", "Kitchen Essentials", "888-999-0000", "Cookware, Kitchen Gadgets"});
        supplierModel.addRow(new Object[]{"S011", "Pet Care", "111-333-5555", "Pet Food, Pet Supplies"});
        supplierModel.addRow(new Object[]{"S012", "Sports Zone", "444-555-6666", "Sports Equipment, Apparel"});
        supplierModel.addRow(new Object[]{"S013", "Garden Center", "777-888-9999", "Plants, Gardening Tools"});
        supplierModel.addRow(new Object[]{"S014", "Music Store", "000-111-2222", "Musical Instruments, Accessories"});
        supplierModel.addRow(new Object[]{"S015", "Toys Galore", "333-444-5555", "Toys, Games"});
        supplierModel.addRow(new Object[]{"S016", "Electro World", "666-777-8888", "Consumer Electronics, Appliances"});
        supplierModel.addRow(new Object[]{"S017", "Home Comfort", "999-000-1111", "Bedding, Home Decor"});
        supplierModel.addRow(new Object[]{"S018", "Health & Wellness", "222-333-4444", "Health Products, Wellness Supplements"});
        supplierModel.addRow(new Object[]{"S019", "Craft Supplies", "555-666-7777", "Art Supplies, Crafts"});
        supplierModel.addRow(new Object[]{"S020", "Office Furniture", "888-999-0000", "Office Furniture, Ergonomic Chairs"});
    }
    

    private void customizeTable() {
        // Customize column widths
        supplierTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Supplier ID
        supplierTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
        supplierTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Contact
        supplierTable.getColumnModel().getColumn(3).setPreferredWidth(300); // Item Data

        // Center-align text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        supplierTable.setDefaultRenderer(Object.class, centerRenderer);

        // Customize table header
        JTableHeader header = supplierTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);

        // Add borders and alternate row colors
        supplierTable.setShowGrid(true);
        supplierTable.setGridColor(Color.LIGHT_GRAY);
        supplierTable.setIntercellSpacing(new Dimension(1, 1));
        supplierTable.setRowHeight(30);

        // Set alternating row colors
        supplierTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        SwingUtilities.invokeLater(() -> new Supplier_Page("Inventory Manager").setVisible(true));
    }
}
