import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HR extends JFrame {
    private final JTable employeeTable;
    private DefaultTableModel employeeModel;
    private String userRole;

    public HR(String role) {
        this.userRole = role;
        setTitle("Human Resource Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a panel for the table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);

        // Table columns: Employee ID, Name, Position, Salary
        employeeModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(employeeModel);
        customizeTable();

        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        // Add sample data
        addSampleData();

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Add Employee Button
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addEmployeeButton.setForeground(Color.WHITE);
        addEmployeeButton.setBackground(new Color(0, 102, 204));
        addEmployeeButton.setFocusPainted(false);
        addEmployeeButton.setBorderPainted(false);
        buttonPanel.add(addEmployeeButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(204, 0, 0));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        buttonPanel.add(backButton);

        // Add panels to the frame
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for add employee button
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Role-based access control for adding employees
                if (userRole.equals("HR Manager") || userRole.equals("Store Manager")) {
                    String employeeID = JOptionPane.showInputDialog("Employee ID:");
                    String name = JOptionPane.showInputDialog("Name:");
                    String position = JOptionPane.showInputDialog("Position:");
                    String salary = JOptionPane.showInputDialog("Salary:");

                    if (employeeID != null && name != null && position != null && salary != null) {
                        employeeModel.addRow(new Object[]{employeeID, name, position, salary});
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have permission to add employees.");
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
        employeeModel.addRow(new Object[]{"E01", "Ava Thompson", "Cashier", "$32,000"});
        employeeModel.addRow(new Object[]{"E02", "Mason Lee", "Store Manager", "$55,000"});
        employeeModel.addRow(new Object[]{"E03", "Isabella Martinez", "Sales Associate", "$28,000"});
        employeeModel.addRow(new Object[]{"E04", "Ethan Walker", "Accountant", "$42,000"});
        employeeModel.addRow(new Object[]{"E05", "Mia Johnson", "HR Specialist", "$37,000"});
        employeeModel.addRow(new Object[]{"E06", "Lucas Davis", "IT Support", "$30,000"});
        employeeModel.addRow(new Object[]{"E07", "Charlotte Brown", "Marketing Coordinator", "$35,000"});
        employeeModel.addRow(new Object[]{"E08", "Noah Wilson", "Logistics Manager", "$48,000"});
        employeeModel.addRow(new Object[]{"E09", "Amelia Scott", "Customer Service", "$29,000"});
        employeeModel.addRow(new Object[]{"E10", "Logan Taylor", "Warehouse Supervisor", "$40,000"});
    }

    private void customizeTable() {
        // Customize column widths
        employeeTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Employee ID
        employeeTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
        employeeTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Position
        employeeTable.getColumnModel().getColumn(3).setPreferredWidth(150); // Salary

        // Center-align text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        employeeTable.setDefaultRenderer(Object.class, centerRenderer);

        // Customize table header
        JTableHeader header = employeeTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);
        
        // Add borders and alternate row colors
        employeeTable.setShowGrid(true);
        employeeTable.setGridColor(Color.LIGHT_GRAY);
        employeeTable.setIntercellSpacing(new Dimension(1, 1));
        employeeTable.setRowHeight(30);
        
        // Set alternating row colors
        employeeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        SwingUtilities.invokeLater(() -> new HR("HR Manager").setVisible(true));
    }
}
