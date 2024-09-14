import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Sales_Record extends JFrame {
    private JTable salesTable;
    private DefaultTableModel salesModel;
    private String userRole;

    public Sales_Record(String role) {
        this.userRole = role;
        setTitle("Sales Records");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table columns: Sale Date, Product Name, Quantity, Total Price
        salesModel = new DefaultTableModel(new String[]{"Sale Date", "Product Name", "Quantity", "Total Price"}, 0);
        salesTable = new JTable(salesModel);

        // Set background color for table header
        JTableHeader tableHeader = salesTable.getTableHeader();
        tableHeader.setBackground(new Color(70, 130, 180)); // Steel Blue
        tableHeader.setForeground(Color.WHITE); // White text

        // Set background color for table cells
        salesTable.setBackground(Color.WHITE); // White background for table cells
        salesTable.setGridColor(Color.LIGHT_GRAY); // Grid color

        // Sample data
        salesModel.addRow(new Object[]{"2024-08-20", "Bluetooth Headphones", "2", "$100"});
        salesModel.addRow(new Object[]{"2024-08-21", "Backpack", "1", "$55"});
        salesModel.addRow(new Object[]{"2024-08-22", "Desk Lamp", "4", "$80"});
        salesModel.addRow(new Object[]{"2024-08-23", "Smartphone Case", "5", "$25"});
        salesModel.addRow(new Object[]{"2024-08-24", "Fitness Tracker", "2", "$120"});
        salesModel.addRow(new Object[]{"2024-08-25", "Running Socks", "6", "$30"});
        salesModel.addRow(new Object[]{"2024-08-26", "Portable Charger", "3", "$45"});
        salesModel.addRow(new Object[]{"2024-08-27", "Yoga Mat", "2", "$60"});
        salesModel.addRow(new Object[]{"2024-08-28", "Coffee Maker", "1", "$85"});
        salesModel.addRow(new Object[]{"2024-08-29", "Electric Kettle", "2", "$40"});

        // Add Sale Button
        JButton addSaleButton = new JButton("Add Sale");
        addSaleButton.setBackground(new Color(34, 139, 34)); // Forest Green
        addSaleButton.setForeground(Color.WHITE);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(220, 20, 60)); // Crimson
        backButton.setForeground(Color.WHITE);

        // Set up layout
        setLayout(new BorderLayout());
        
        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245)); // Light Gray
        mainPanel.add(new JScrollPane(salesTable), BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(245, 245, 245)); // Light Gray
        buttonPanel.add(addSaleButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

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
                new Main_Dashboard(role).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Sales_Record("Sales Manager").setVisible(true));
    }
}
