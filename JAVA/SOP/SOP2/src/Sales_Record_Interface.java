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
        setTitle("Sales Records");
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

        // Left panel for sales table and buttons
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
        JLabel titleLabel = new JLabel("Sales Records", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 102));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table setup
        salesModel = new DefaultTableModel(new String[]{"Sale Date", "Product Name", "Quantity", "Total Price"}, 0);
        salesTable = new JTable(salesModel);
        JScrollPane tableScrollPane = new JScrollPane(salesTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Sample data
        salesModel.addRow(new Object[]{"17-08-2024", "Leather Jacket", "1", "$120"});
        salesModel.addRow(new Object[]{"18-08-2024", "Sneakers", "2", "$120"});
        salesModel.addRow(new Object[]{"19-08-2024", "Handbag", "1", "$80"});

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Add Sale Button
        JButton addSaleButton = new JButton("Add Sale");
        addSaleButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addSaleButton.setBackground(new Color(0, 102, 102));
        addSaleButton.setForeground(Color.WHITE);
        buttonPanel.add(addSaleButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setBackground(new Color(0, 102, 102));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for add sale button
        addSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Role-based access control for adding sales
                if (userRole.equals("Sales Manager") || userRole.equals("Store Manager")) {
                    String saleDate = JOptionPane.showInputDialog("Sale Date (DD-MM-YYYY):");
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

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Sales_Record_Interface("Sales Manager").setVisible(true));
    }
}


