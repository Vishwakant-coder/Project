import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Sales_Record_Page extends JFrame {
    private JTable salesTable;
    private DefaultTableModel salesModel;
    private String userRole;

    public Sales_Record_Page(String role) {
        this.userRole = role;
        setTitle(" Sales Records");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table columns: Sale Date, Product Name, Quantity, Total Price
        salesModel = new DefaultTableModel(new String[]{"Sale Date", "Product Name", "Quantity", "Total Price"}, 0);
        salesTable = new JTable(salesModel);
        customizeTable();

        JScrollPane tableScrollPane = new JScrollPane(salesTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Add sample data
        addSampleData();

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Add Sale Button
        JButton addSaleButton = new JButton("Add Sale");
        addSaleButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addSaleButton.setForeground(Color.WHITE);
        addSaleButton.setBackground(new Color(0, 102, 204));
        addSaleButton.setFocusPainted(false);
        addSaleButton.setBorderPainted(false);
        buttonPanel.add(addSaleButton);

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
                new Dashboard(userRole).setVisible(true);
            }
        });
    }

    private void addSampleData() {
        salesModel.addRow(new Object[]{"2024-08-04", "Sneakers", "1", "$50"});
        salesModel.addRow(new Object[]{"2024-08-05", "Hat", "4", "$28"});
        salesModel.addRow(new Object[]{"2024-08-06", "Sunglasses", "2", "$40"});
        salesModel.addRow(new Object[]{"2024-08-07", "Sweater", "1", "$35"});
        salesModel.addRow(new Object[]{"2024-08-08", "Jacket", "1", "$80"});
        salesModel.addRow(new Object[]{"2024-08-09", "Shorts", "3", "$30"});
        salesModel.addRow(new Object[]{"2024-08-10", "Scarf", "2", "$22"});
        salesModel.addRow(new Object[]{"2024-08-11", "Dress", "1", "$60"});
        salesModel.addRow(new Object[]{"2024-08-12", "Boots", "1", "$70"});
        salesModel.addRow(new Object[]{"2024-08-13", "Blouse", "2", "$45"});
        salesModel.addRow(new Object[]{"2024-08-14", "Skirt", "3", "$30"});
        salesModel.addRow(new Object[]{"2024-08-15", "Belt", "1", "$15"});
    }
    

    private void customizeTable() {
        // Customize column widths
        salesTable.getColumnModel().getColumn(0).setPreferredWidth(120); // Sale Date
        salesTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Product Name
        salesTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Quantity
        salesTable.getColumnModel().getColumn(3).setPreferredWidth(120); // Total Price

        // Center-align text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        salesTable.setDefaultRenderer(Object.class, centerRenderer);

        // Customize table header
        JTableHeader header = salesTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);
        
        // Add borders and alternate row colors
        salesTable.setShowGrid(true);
        salesTable.setGridColor(Color.LIGHT_GRAY);
        salesTable.setIntercellSpacing(new Dimension(1, 1));
        salesTable.setRowHeight(30);
        
        // Set alternating row colors
        salesTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        SwingUtilities.invokeLater(() -> new Sales_Record_Page("Sales Manager").setVisible(true));
    }
}
