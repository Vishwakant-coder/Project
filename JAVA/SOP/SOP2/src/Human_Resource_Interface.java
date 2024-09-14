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
        setTitle("Human Resource Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // Right panel
        JPanel rightPanel = createRightPanel();
        mainPanel.add(rightPanel, BorderLayout.WEST);

        // Left panel
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
        JLabel titleLabel = new JLabel("Human Resource Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 102));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table
        employeeModel = new DefaultTableModel(new String[]{"Employee ID", "Name", "Position", "Salary"}, 0);
        employeeTable = new JTable(employeeModel);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Sample data
        employeeModel.addRow(new Object[]{"E001", "Alice Williams", "HR Manager", "$55,000"});
        employeeModel.addRow(new Object[]{"E002", "Bob Johnson", "Store Manager", "$50,000"});
        employeeModel.addRow(new Object[]{"E003", "Charlie Brown", "Sales Associate", "$30,000"});
        employeeModel.addRow(new Object[]{"E004", "David Smith", "IT Specialist", "$45,000"});
        employeeModel.addRow(new Object[]{"E005", "Eva Taylor", "Marketing Manager", "$60,000"});

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Add Employee Button
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addEmployeeButton.setBackground(new Color(0, 102, 102));
        addEmployeeButton.setForeground(Color.WHITE);
        buttonPanel.add(addEmployeeButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setBackground(new Color(0, 102, 102));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for add employee button
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
        SwingUtilities.invokeLater(() -> new Human_Resource_Interface("HR Manager").setVisible(true));
    }
}
