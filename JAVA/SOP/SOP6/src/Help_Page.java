import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Help_Page extends JFrame {

    public Help_Page() {
        setTitle("Help Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        // Title
        JLabel titleLabel = new JLabel("Help Page", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 204)); // Professional blue color
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Help Text Area
        JTextArea helpTextArea = new JTextArea();
        helpTextArea.setEditable(false);
        helpTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        helpTextArea.setText(
            "   - View Schedules: Review and manage staff schedules.\n\n" +
                        "Welcome to the BF POS System Help Page\n\n" +
                        "1. Main Dashboard:\n" +
                        "   - Inventory Management: Oversee your store's inventory, including adding, updating, or removing products.\n" +
                        "   - Sales Records: Manage and review sales transactions.\n" +
                        "   - Human Resources: Handle staff details, roles, and schedules.\n" +
                        "   - Supplier Communication: Interact with suppliers and manage orders.\n\n" +
                        "2. Inventory Management:\n" +
                        "   - View Inventory: Display a list of inventory items, their quantities, and details.\n" +
                        "   - Add Product: Introduce new products with details such as name, quantity, and price.\n" +
                        "   - Update Product: Modify details of existing products.\n" +
                        "   - Delete Product: Remove products from the inventory.\n\n" +
                        "3. Sales Records:\n" +
                        "   - View Sales: Access and review all sales transactions.\n" +
                        "   - Search Sales: Locate specific sales transactions by date, item, or customer.\n" +
                        "   - Generate Reports: Create and view sales reports for various periods.\n\n" +
                        "4. Human Resources:\n" +
                        "   - Manage Staff: View, add, and update staff information.\n" +
                        "   - Manage Roles: Assign and update staff roles.\n" +
            "5. Supplier Communication:\n" +
            "   - View Suppliers: Access a list of suppliers with their contact details and supplied items.\n" +
            "   - Update Item Data: Edit item data for specific suppliers.\n" +
            "   - Communicate with Suppliers: Send messages or update orders with suppliers.\n\n" +
            "6. Receipt Page:\n" +
            "   - Customer Name: Enter the name of the customer.\n" +
            "   - Item: Enter the item name.\n" +
            "   - Price: Input the price of the item.\n" +
            "   - Quantity: Specify the quantity of the item.\n" +
            "   - Total: Automatically calculated based on price and quantity.\n" +
            "   - Add Item: Include the item in the receipt.\n" +
            "   - Print: Print the receipt.\n" +
            "   - Clear: Remove all fields and reset the receipt.\n" +
            "   - Back: Return to the Main Dashboard.\n\n" +
            "For further assistance, please contact support."  
        );
        helpTextArea.setMargin(new Insets(10, 10, 10, 10));
        helpTextArea.setLineWrap(true);
        helpTextArea.setWrapStyleWord(true);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(helpTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Custom Scroll Bar UI
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100); // Thumb color
                this.trackColor = new Color(230, 230, 230); // Track color
                this.thumbDarkShadowColor = new Color(80, 80, 80); // Thumb shadow color
                this.thumbHighlightColor = new Color(180, 180, 180); // Thumb highlight color
            }
        });

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100); // Thumb color
                this.trackColor = new Color(230, 230, 230); // Track color
                this.thumbDarkShadowColor = new Color(80, 80, 80); // Thumb shadow color
                this.thumbHighlightColor = new Color(180, 180, 180); // Thumb highlight color
            }
        });

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(0, 102, 204)); // Matching blue background
        backButton.setForeground(Color.WHITE); // White text
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            this.dispose();
            new Dashboard("Store Manager").setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Help_Page().setVisible(true);
        });
    }
}
