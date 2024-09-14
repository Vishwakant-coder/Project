import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Human_Resource_Interface extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel employeeModel;
    private String userRole;

    public Human_Resource_Interface(String role) {
        this.userRole = role;
        setTitle("BF POS System - Human Resource Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // Table columns: Employee ID, Name, Position, Salary
        employeeModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(employeeModel);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tableScrollPane.setBounds(5, 10, 780, 400);  // Set bounds to fit inside the background

        // Sample data
        employeeModel.addRow(new Object[]{"E001", "John Doe", "Cashier", "$30,000"});
        employeeModel.addRow(new Object[]{"E002", "Jane Smith", "Store Manager", "$50,000"});
        employeeModel.addRow(new Object[]{"E003", "Emily Johnson", "Sales Associate", "$25,000"});
        employeeModel.addRow(new Object[]{"E004", "Michael Brown", "Accountant", "$40,000"});
        employeeModel.addRow(new Object[]{"E005", "Sarah Davis", "HR Specialist", "$35,000"});
        employeeModel.addRow(new Object[]{"E006", "James Wilson", "IT Support", "$28,000"});
        employeeModel.addRow(new Object[]{"E007", "Olivia Martinez", "Marketing Coordinator", "$32,000"});
        employeeModel.addRow(new Object[]{"E008", "Daniel Garcia", "Logistics Manager", "$45,000"});
        employeeModel.addRow(new Object[]{"E009", "Sophia Lee", "Customer Service", "$27,000"});
        employeeModel.addRow(new Object[]{"E010", "Liam Anderson", "Warehouse Supervisor", "$38,000"});

        // Add Employee Button
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBounds(5, 410, 150, 30);  // Set bounds for button

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(670, 410, 100, 30);  // Set bounds for back button

        // Add components to the layered pane
        layeredPane.add(tableScrollPane, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(addEmployeeButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);

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
        SwingUtilities.invokeLater(() -> new Human_Resource_Interface("HR Manager").setVisible(true));
    }
}

