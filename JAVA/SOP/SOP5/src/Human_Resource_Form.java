import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class Human_Resource_Form extends JFrame {

    private JTable employeeTable;
    private DefaultTableModel employeeModel;
    private String userRole;

    public Human_Resource_Form(String role) {
        this.userRole = role;
        setTitle("Human Resource Management");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // **Create a main panel with GridBagLayout**
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        getContentPane().add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.BOTH;

        // **Create the employee table with enhanced styling**
        employeeModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(employeeModel);
        employeeTable.setBorder(new LineBorder(Color.GRAY));
        employeeTable.setRowHeight(30);
        employeeTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        employeeTable.setBackground(Color.WHITE);
        employeeTable.setFillsViewportHeight(true);

        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tableScrollPane.setBorder(new LineBorder(Color.GRAY));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridheight = 1;
        mainPanel.add(tableScrollPane, gbc);

        // **Add Employee Button**
        JButton addEmployeeButton = new JButton("Add Employee");
        styleButton(addEmployeeButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        mainPanel.add(addEmployeeButton, gbc);

        // **Back Button**
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        mainPanel.add(backButton, gbc);

        // **Add sample data to the table**
        addSampleData();

        // **Action listener for add employee button**
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        // **Action listener for back button**
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main_Dashboard(userRole).setVisible(true);
            }
        });
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 30));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setBorder(new LineBorder(new Color(0, 102, 204)));
    }

    private void addSampleData() {
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Human_Resource_Form("HR Manager").setVisible(true));
    }
}
