import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

        // Set light black (dark gray) background color for the frame and panels
        getContentPane().setBackground(Color.DARK_GRAY);

        // Table columns: Sale Date, Product Name, Quantity, Total Price
        salesModel = new DefaultTableModel(new String[]{"Sale Date", "Product Name", "Quantity", "Total Price"}, 0);
        salesTable = new JTable(salesModel);
        JScrollPane tableScroll = new JScrollPane(salesTable);
        tableScroll.setBounds(5, 10, 780, 300);

        // New sample data
        salesModel.addRow(new Object[]{"2024-08-01", "Smartphone", "5", "$500"});
        salesModel.addRow(new Object[]{"2024-08-02", "Laptop", "2", "$1,200"});
        salesModel.addRow(new Object[]{"2024-08-03", "Headphones", "10", "$300"});
        salesModel.addRow(new Object[]{"2024-08-04", "Smartwatch", "3", "$240"});
        salesModel.addRow(new Object[]{"2024-08-05", "Tablet", "4", "$400"});
        salesModel.addRow(new Object[]{"2024-08-06", "Camera", "1", "$800"});
        salesModel.addRow(new Object[]{"2024-08-07", "Bluetooth Speaker", "7", "$210"});
        salesModel.addRow(new Object[]{"2024-08-08", "Wireless Mouse", "15", "$75"});
        salesModel.addRow(new Object[]{"2024-08-09", "Keyboard", "8", "$160"});
        salesModel.addRow(new Object[]{"2024-08-10", "Monitor", "6", "$900"});
        salesModel.addRow(new Object[]{"2024-08-11", "Printer", "3", "$450"});
        salesModel.addRow(new Object[]{"2024-08-12", "External Hard Drive", "5", "$250"});
        salesModel.addRow(new Object[]{"2024-08-13", "USB Flash Drive", "20", "$200"});
        salesModel.addRow(new Object[]{"2024-08-14", "Router", "8", "$320"});
        salesModel.addRow(new Object[]{"2024-08-15", "Gaming Console", "4", "$1,600"});
        salesModel.addRow(new Object[]{"2024-08-16", "VR Headset", "2", "$500"});
        salesModel.addRow(new Object[]{"2024-08-17", "Smart Light Bulbs", "10", "$150"});
        salesModel.addRow(new Object[]{"2024-08-18", "Security Camera", "6", "$900"});
        salesModel.addRow(new Object[]{"2024-08-19", "Drone", "1", "$1,200"});
        salesModel.addRow(new Object[]{"2024-08-20", "Fitness Tracker", "12", "$600"});

        JButton addSaleButton = new JButton("Add Sale");
        addSaleButton.setBackground(Color.DARK_GRAY);
        addSaleButton.setForeground(Color.WHITE);
        addSaleButton.addActionListener(new AddSaleActionListener());

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new BackButtonActionListener());

        // Set up layout
        setLayout(new BorderLayout());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);  // Set background color for the button panel
        buttonPanel.add(addSaleButton);
        buttonPanel.add(backButton);

        add(new JScrollPane(salesTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Action listener for add sale button
    private class AddSaleActionListener implements ActionListener {
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
    }

    // Action listener for back button
    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new Main_Dashboard(userRole).setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Sales_Record("Sales Manager").setVisible(true));
    }
}
