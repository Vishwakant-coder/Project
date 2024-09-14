import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Supplier_Communication extends JFrame {
    private JTable supplierTable;
    private DefaultTableModel supplierModel;
    private String userRole;

    public Supplier_Communication(String role) {
        this.userRole = role;
        setTitle("BF POS System - Supplier Communication");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.DARK_GRAY);  // Set light black background for the layered pane
        layeredPane.setOpaque(true);
        setContentPane(layeredPane);

        // Table columns: Supplier ID, Name, Contact, Item Data
        supplierModel = new DefaultTableModel(new String[]{"Supplier ID", "Name", "Contact", "Item Data"}, 0);
        supplierTable = new JTable(supplierModel);
        JScrollPane tableScrollPane = new JScrollPane(supplierTable);
        tableScrollPane.setBounds(5, 10, 780, 300);  // Set bounds to fit inside the background

        // Sample data
        supplierModel.addRow(new Object[]{"S001", "ABC Supplies", "123-456-7890", "Widgets, Gadgets"});
        supplierModel.addRow(new Object[]{"S002", "XYZ Distributors", "987-654-3210", "Tools, Parts"});
        supplierModel.addRow(new Object[]{"S003", "PQR Traders", "456-789-0123", "Electronics, Accessories"});
        supplierModel.addRow(new Object[]{"S004", "LMN Suppliers", "321-654-9870", "Furniture, Fixtures"});

        // Form fields
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        JLabel itemDataLabel = new JLabel("Item Data:");
        JTextField supplierIDField = new JTextField(10);
        JTextField itemDataField = new JTextField(20);

        JButton updateItemDataButton = new JButton("Update Item Data");
        JButton backButton = new JButton("Back");  // Create Back button

        // Set button colors
        updateItemDataButton.setBackground(Color.DARK_GRAY);
        updateItemDataButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);

        // Set label colors
        supplierIDLabel.setForeground(Color.WHITE);
        itemDataLabel.setForeground(Color.WHITE);

        // Layout for form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2));
        formPanel.add(supplierIDLabel);
        formPanel.add(supplierIDField);
        formPanel.add(itemDataLabel);
        formPanel.add(itemDataField);
        formPanel.setBounds(5, 320, 780, 70);  // Set bounds for the form panel
        formPanel.setBackground(Color.DARK_GRAY);  // Set light black background for the form panel

        // Add components to the layered pane
        layeredPane.add(tableScrollPane, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(formPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(updateItemDataButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);  // Add Back button

        // Set bounds for buttons
        updateItemDataButton.setBounds(5, 410, 150, 30);
        backButton.setBounds(670, 410, 100, 30);  // Set bounds for Back button

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
                new Main_Dashboard(role).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Supplier_Communication("Inventory Manager").setVisible(true));
    }
}
