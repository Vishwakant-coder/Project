import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Human_Resource extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel employeeModel;
    private String userRole;

    public Human_Resource(String role) {
        this.userRole = role;
        setTitle("Human Resource Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set background color for the main panel
        mainPanel.setBackground(new Color(240, 248, 255)); // Light Blue

        // Table columns: Employee ID, Name, Position, Salary
        employeeModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(employeeModel);

        // Set background color for table header
        JTableHeader tableHeader = employeeTable.getTableHeader();
        tableHeader.setBackground(new Color(70, 130, 180)); // Steel Blue
        tableHeader.setForeground(Color.WHITE);

        // Set background color for table cells
        employeeTable.setBackground(Color.WHITE); // White background for table cells
        employeeTable.setGridColor(Color.LIGHT_GRAY); // Grid color

        // Sample data
        employeeModel.addRow(new Object[]{"E001", "Alice Brown", "HR Manager", "$55,000"});
        employeeModel.addRow(new Object[]{"E002", "Bob White", "Store Manager", "$52,000"});
        employeeModel.addRow(new Object[]{"E003", "Carol Green", "Sales Associate", "$28,000"});
        employeeModel.addRow(new Object[]{"E004", "David Black", "Accountant", "$42,000"});
        employeeModel.addRow(new Object[]{"E005", "Eve Blue", "Customer Service", "$30,000"});
        employeeModel.addRow(new Object[]{"E006", "Frank Gray", "IT Support", "$29,000"});
        employeeModel.addRow(new Object[]{"E007", "Grace Yellow", "Marketing Coordinator", "$34,000"});
        employeeModel.addRow(new Object[]{"E008", "Hank Pink", "Logistics Manager", "$48,000"});
        employeeModel.addRow(new Object[]{"E009", "Ivy Orange", "Sales Manager", "$40,000"});
        employeeModel.addRow(new Object[]{"E010", "Jack Red", "Warehouse Supervisor", "$39,000"});

        // Table scroll pane
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tableScrollPane.setPreferredSize(new Dimension(760, 300));  // Set preferred size for the table scroll pane

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(tableScrollPane, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255)); // Same background as main panel

        // Add Employee Button
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBackground(new Color(34, 139, 34)); // Forest Green
        addEmployeeButton.setForeground(Color.WHITE);
        buttonPanel.add(addEmployeeButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(220, 20, 60)); // Crimson
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);

        // Add button panel to main panel
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
        mainPanel.add(buttonPanel, gbc);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

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
                new Main_Dashboard(role).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Human_Resource("HR Manager").setVisible(true));
    }
}
