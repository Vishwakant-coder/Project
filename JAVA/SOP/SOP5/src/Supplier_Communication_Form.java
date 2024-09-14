import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Supplier_Communication_Form extends JFrame {
    private JTable supplierTable;
    private DefaultTableModel supplierModel;
    private String userRole;

    public Supplier_Communication_Form(String role) {
        this.userRole = role;
        setTitle("Supplier Communication");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // Table columns: Supplier ID, Name, Contact, Item Data
        supplierModel = new DefaultTableModel(new String[]{"Supplier ID", "Name", "Contact", "Item Data"}, 0);
        supplierTable = new JTable(supplierModel);
        supplierTable.setFillsViewportHeight(true);
        supplierTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        supplierTable.setRowHeight(25);
        
        // Sample data
        supplierModel.addRow(new Object[]{"S001", "Gizmo Corp", "212-555-1234", "Gadgets, Widgets"});
        supplierModel.addRow(new Object[]{"S002", "Tech Supplies Inc.", "310-555-5678", "Computers, Accessories"});
        supplierModel.addRow(new Object[]{"S003", "Home Essentials", "718-555-8765", "Furniture, Appliances"});
        supplierModel.addRow(new Object[]{"S004", "Fashion Forward", "305-555-4321", "Clothing, Accessories"});
        supplierModel.addRow(new Object[]{"S005", "Office Plus", "415-555-6789", "Office Supplies, Furniture"});

        JScrollPane tableScrollPane = new JScrollPane(supplierTable);
        tableScrollPane.setBounds(5, 10, 780, 300);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Form fields
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLabel itemDataLabel = new JLabel("Item Data:");
        itemDataLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField supplierIDField = new JTextField(10);
        JTextField itemDataField = new JTextField(20);

        JButton updateItemDataButton = new JButton("Update Item Data");
        updateItemDataButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateItemDataButton.setPreferredSize(new Dimension(150, 30));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(100, 30));

        // Layout for form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        formPanel.add(supplierIDLabel);
        formPanel.add(supplierIDField);
        formPanel.add(itemDataLabel);
        formPanel.add(itemDataField);
        formPanel.setBounds(5, 320, 780, 70);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.add(updateItemDataButton);
        buttonPanel.add(backButton);
        buttonPanel.setBounds(0, 400, 800, 50);

        // Add components to the layered pane
        layeredPane.add(tableScrollPane, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(formPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

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
                new Main_Dashboard(userRole).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Supplier_Communication_Form("Inventory Manager").setVisible(true));
    }
}
