import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Sales_Record_Interface extends JFrame {
    private JTable salesTable;
    private DefaultTableModel salesModel;
    private String userRole;

    public Sales_Record_Interface(String role) {
        this.userRole = role;
        setTitle("BF POS System - Sales Records");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table columns: Sale Date, Product Name, Quantity, Total Price
        salesModel = new DefaultTableModel(new String[]{"Sale Date", "Product Name", "Quantity", "Total Price"}, 0);
        salesTable = new JTable(salesModel);

        // Sample data
        salesModel.addRow(new Object[]{"2024-08-01", "T-Shirt", "2", "$20"});
        salesModel.addRow(new Object[]{"2024-08-02", "Jeans", "1", "$20"});
        salesModel.addRow(new Object[]{"2024-08-03", "Black-T-shirt", "3", "$60"});

        // Add Sale Button
        JButton addSaleButton = new JButton("Add Sale");
        // Back Button
        JButton backButton = new JButton("Back");

        // Set up layout
        setLayout(new BorderLayout());
        add(new JScrollPane(salesTable), BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addSaleButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

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
        SwingUtilities.invokeLater(() -> new Sales_Record_Interface("Sales Manager").setVisible(true));
    }
}
