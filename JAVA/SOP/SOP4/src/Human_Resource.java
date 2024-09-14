import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

        // Create a layered pane and set its background color to black
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.BLACK);
        layeredPane.setOpaque(true);  // Make the background color visible
        setContentPane(layeredPane);

        // Table columns: Employee ID, Name, Position, Salary
        employeeModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(employeeModel);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tableScrollPane.setBounds(5, 10, 780, 380);  // Adjusted bounds to fit better

        // Set table background and foreground colors
        employeeTable.setBackground(Color.WHITE);
        employeeTable.setForeground(Color.BLACK);
        employeeTable.setGridColor(Color.GRAY);  // Set grid lines color

        // Sample data
        addSampleData();

        // Add Employee Button
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBounds(10, 400, 150, 30);
        addEmployeeButton.setBackground(Color.DARK_GRAY);
        addEmployeeButton.setForeground(Color.WHITE);
        addEmployeeButton.addActionListener(new AddEmployeeActionListener());

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(670, 400, 100, 30);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new BackButtonActionListener());

        // Add components to the layered pane
        layeredPane.add(tableScrollPane, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(addEmployeeButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);
    }

    private void addSampleData() {
        // Adds sample employee data to the table
        employeeModel.addRow(new Object[]{"E201", "Jessica Adams", "Chief Financial Officer", "$90,000"});
        employeeModel.addRow(new Object[]{"E202", "Ryan Brown", "Senior Software Engineer", "$85,000"});
        employeeModel.addRow(new Object[]{"E203", "Samantha Green", "Product Manager", "$78,000"});
        employeeModel.addRow(new Object[]{"E204", "Michael Thompson", "Sales Executive", "$65,000"});
        employeeModel.addRow(new Object[]{"E205", "Emily Wilson", "Business Analyst", "$70,000"});
        employeeModel.addRow(new Object[]{"E206", "James Robinson", "Customer Support Lead", "$60,000"});
        employeeModel.addRow(new Object[]{"E207", "Linda Martinez", "HR Coordinator", "$55,000"});
        employeeModel.addRow(new Object[]{"E208", "David Harris", "Marketing Specialist", "$62,000"});
        employeeModel.addRow(new Object[]{"E209", "Olivia Clark", "UX/UI Designer", "$68,000"});
        employeeModel.addRow(new Object[]{"E210", "Chris Lewis", "IT Support Specialist", "$53,000"});
    }

    // ActionListener for adding employees
    private class AddEmployeeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Role-based access control for adding employees
            if (userRole.equals("HR Manager") || userRole.equals("Store Manager")) {
                JTextField employeeIDField = new JTextField();
                JTextField nameField = new JTextField();
                JTextField positionField = new JTextField();
                JTextField salaryField = new JTextField();

                Object[] fields = {
                    "Employee ID:", employeeIDField,
                    "Name:", nameField,
                    "Position:", positionField,
                    "Salary:", salaryField
                };

                int option = JOptionPane.showConfirmDialog(null, fields, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String employeeID = employeeIDField.getText();
                    String name = nameField.getText();
                    String position = positionField.getText();
                    String salary = salaryField.getText();

                    if (!employeeID.isEmpty() && !name.isEmpty() && !position.isEmpty() && !salary.isEmpty()) {
                        employeeModel.addRow(new Object[]{employeeID, name, position, salary});
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "You do not have permission to add employees.", "Access Denied", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ActionListener for back button
    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new Main_Dashboard(userRole).setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Human_Resource("HR Manager").setVisible(true));
    }
}
