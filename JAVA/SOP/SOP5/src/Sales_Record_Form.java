import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Sales_Record_Form extends JFrame {
    private JTable salesTable;
    private DefaultTableModel salesModel;
    private String userRole;

    public Sales_Record_Form(String role) {
        this.userRole = role;
        setTitle("Sales Records");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // Table columns: Sale Date, Product Name, Quantity, Total Price
        salesModel = new DefaultTableModel(new String[]{"Sale Date", "Product Name", "Quantity", "Total Price"}, 0);
        salesTable = new JTable(salesModel);
        salesTable.setFillsViewportHeight(true);
        salesTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        salesTable.setRowHeight(25);

        // Sample data
        salesModel.addRow(new Object[]{"2024-08-15", "Summer Dress", "4", "$80"});
        salesModel.addRow(new Object[]{"2024-08-16", "Winter Jacket", "1", "$120"});
        salesModel.addRow(new Object[]{"2024-08-17", "Running Shoes", "2", "$90"});
        salesModel.addRow(new Object[]{"2024-08-18", "Sneakers", "3", "$75"});
        salesModel.addRow(new Object[]{"2024-08-19", "Hat", "6", "$45"});
        salesModel.addRow(new Object[]{"2024-08-20", "Scarf", "5", "$50"});
        salesModel.addRow(new Object[]{"2024-08-21", "Gloves", "7", "$35"});
        salesModel.addRow(new Object[]{"2024-08-22", "Sweater", "3", "$60"});
        salesModel.addRow(new Object[]{"2024-08-23", "Sunglasses", "8", "$160"});
        salesModel.addRow(new Object[]{"2024-08-24", "Bag", "2", "$110"});

        JScrollPane tableScrollPane = new JScrollPane(salesTable);
        tableScrollPane.setBounds(5, 10, 780, 400);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Add Sale Button
        JButton addSaleButton = new JButton("Add Sale");
        addSaleButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addSaleButton.setPreferredSize(new Dimension(150, 30));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(150, 30));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.add(addSaleButton);
        buttonPanel.add(backButton);
        buttonPanel.setBounds(0, 410, 800, 50);

        // Add components to the layered pane
        layeredPane.add(tableScrollPane, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        // Action listener for add sale button
        addSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Role-based access control for adding sales
                if (userRole.equals("Sales Manager") || userRole.equals("Store Manager")) {
                    String saleDate = JOptionPane.showInputDialog("Sale Date (YYYY-MM-DD):");
                    String productName = JOptionPane.showInputDialog("Product Name:");
                    String quantity = JOptionPane.showInputDialog("Quantity:");
                    String totalPrice = JOptionPane.showInputDialog("Total Price:");

                    if (saleDate != null && productName != null && quantity != null && totalPrice != null) {
                        salesModel.addRow(new Object[]{saleDate, productName, quantity, totalPrice});
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have permission to add sales.");
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
        SwingUtilities.invokeLater(() -> new Sales_Record_Form("Sales Manager").setVisible(true));
    }
}
