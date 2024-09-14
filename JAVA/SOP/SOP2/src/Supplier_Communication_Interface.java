import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Supplier_Communication_Interface extends JFrame {
    private JTable supplierTable;
    private DefaultTableModel supplierModel;
    private String userRole;

    public Supplier_Communication_Interface(String role) {
        this.userRole = role;
        setTitle("Supplier Communication");
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

        // Left panel for supplier table, form, and buttons
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
        JLabel titleLabel = new JLabel("Supplier Communication", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 102));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table setup
        supplierModel = new DefaultTableModel(new String[]{"Supplier ID", "Name", "Contact", "Item Data"}, 0);
        supplierTable = new JTable(supplierModel);
        JScrollPane tableScrollPane = new JScrollPane(supplierTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // New sample data
        supplierModel.addRow(new Object[]{"S101", "GreenTech Corp", "555-0123", "Eco-friendly Gadgets"});
        supplierModel.addRow(new Object[]{"S102", "BrightFuture Ltd", "555-0456", "Solar Panels, Batteries"});
        supplierModel.addRow(new Object[]{"S103", "UrbanWear Inc.", "555-0789", "Sustainable Clothing"});
        supplierModel.addRow(new Object[]{"S104", "SmartSolutions", "555-0912", "Smart Home Devices"});

        // Form fields
        JPanel formPanel = new JPanel(new GridLayout(2, 2));
        formPanel.setBackground(Color.WHITE);
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        JLabel itemDataLabel = new JLabel("Item Data:");
        JTextField supplierIDField = new JTextField(10);
        JTextField itemDataField = new JTextField(20);

        formPanel.add(supplierIDLabel);
        formPanel.add(supplierIDField);
        formPanel.add(itemDataLabel);
        formPanel.add(itemDataField);

        panel.add(formPanel, BorderLayout.SOUTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Update Item Data Button
        JButton updateItemDataButton = new JButton("Update Item Data");
        updateItemDataButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        updateItemDataButton.setBackground(new Color(0, 102, 102));
        updateItemDataButton.setForeground(Color.WHITE);
        buttonPanel.add(updateItemDataButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setBackground(new Color(0, 102, 102));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

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

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Supplier_Communication_Interface("Inventory Manager").setVisible(true));
    }
}
