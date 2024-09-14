import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class HelpPage extends JFrame {

    public HelpPage() {
        setTitle("BF POS System - Help Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background Color
        getContentPane().setBackground(Color.WHITE);

        // Main Title
        JLabel titleLabel = new JLabel("Help Page");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(30, 20, 200, 30);
        add(titleLabel);

        // Help Text Area
        JTextArea helpTextArea = new JTextArea();
        helpTextArea.setEditable(false);
        helpTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        helpTextArea.setText(
            "Welcome to the BF POS System Help Page.\n\n" +
            "1. **Main Dashboard**:\n" +
            "   - **Inventory Management**: Manage your store's inventory, add, update, or delete products.\n" +
            "   - **Sales Records**: View and manage sales transactions.\n" +
            "   - **Human Resources**: Manage staff details, roles, and schedules.\n" +
            "   - **Supplier Communication**: Communicate with suppliers and manage orders.\n\n" +
            "2. **Inventory Management**:\n" +
            "   - **View Inventory**: View the list of inventory items, their quantities, and other details.\n" +
            "   - **Add Product**: Add new products to the inventory with details like name, quantity, and price.\n" +
            "   - **Update Product**: Modify existing product details.\n" +
            "   - **Delete Product**: Remove products from the inventory.\n\n" +
            "3. **Sales Records**:\n" +
            "   - **View Sales**: Review all sales transactions.\n" +
            "   - **Search Sales**: Search for specific sales transactions by date, item, or customer.\n" +
            "   - **Generate Reports**: Generate and view sales reports for different time periods.\n\n" +
            "4. **Human Resources**:\n" +
            "   - **Manage Staff**: View, add, and update staff details.\n" +
            "   - **Manage Roles**: Assign and update roles for staff members.\n" +
            "   - **View Schedules**: Check and manage staff schedules.\n\n" +
            "5. **Supplier Communication**:\n" +
            "   - **View Suppliers**: List of suppliers with details like contact information and supplied items.\n" +
            "   - **Update Item Data**: Update the item data for specific suppliers.\n" +
            "   - **Communicate with Suppliers**: Send messages or update orders with suppliers.\n\n" +
            "6. **Receipt Page**:\n" +
            "   - **Customer Name**: Enter the customer's name for the receipt.\n" +
            "   - **Item**: Enter the item name.\n" +
            "   - **Price**: Enter the price of the item.\n" +
            "   - **Quantity**: Enter the quantity of the item.\n" +
            "   - **Total**: Automatically calculated based on price and quantity.\n" +
            "   - **Add Item**: Adds the item to the receipt.\n" +
            "   - **Print**: Prints the receipt.\n" +
            "   - **Clear**: Clears all fields and the receipt.\n" +
            "   - **Back**: Returns to the Main Dashboard.\n\n" +
            "If you need further assistance, please contact support."
        );

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(helpTextArea);
        scrollPane.setBounds(30, 70, 740, 400);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(scrollPane);

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
        backButton.setBounds(650, 500, 100, 40);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 53, 69)); // Red background
        backButton.setForeground(Color.WHITE); // White text
        backButton.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 1));
        backButton.addActionListener(e -> {
            this.dispose();
            new Main_Dashboard("Store Manager").setVisible(true);
        });
        add(backButton);

        setLayout(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HelpPage().setVisible(true);
        });
    }
}
