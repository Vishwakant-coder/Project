import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Receipt_Page extends JFrame implements ActionListener {
    private JTextArea receiptTextArea;
    private JTextField customerNameField, itemField, priceField, quantityField, totalField;
    private JButton addItemButton, printButton, clearButton, backButton;

    private static final Font LABEL_FONT = new Font("Raleway", Font.BOLD, 16);
    private static final Color BACKGROUND_COLOR = Color.WHITE; // White background for the frame
    private static final Color TEXT_COLOR = Color.BLACK; // Black text for visibility on white background
    private static final Color BUTTON_COLOR_BLUE = new Color(0, 122, 204); // Blue color for buttons
    private static final Color BUTTON_COLOR_RED = new Color(204, 0, 0); // Red color for buttons
    private static final LineBorder FIELD_BORDER = new LineBorder(Color.GRAY, 1); // Standard border

    public Receipt_Page() {
        setTitle(" Receipt Printing");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background Color
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(null);

        // Create components
        createLabel("Customer Name:", 30, 20);
        customerNameField = createTextField(180, 20);

        createLabel("Item:", 30, 70);
        itemField = createTextField(180, 70);

        createLabel("Price:", 30, 120);
        priceField = createTextField(180, 120);

        createLabel("Quantity:", 30, 170);
        quantityField = createTextField(180, 170);

        createLabel("Total:", 30, 220);
        totalField = createTextField(180, 220);
        totalField.setEditable(false);

        addItemButton = createButton("Add Item", 400, 70, BUTTON_COLOR_BLUE);
        printButton = createButton("Print", 150, 500, BUTTON_COLOR_BLUE);
        clearButton = createButton("Clear", 325, 500, BUTTON_COLOR_RED);
        backButton = createButton("Back", 500, 500, BUTTON_COLOR_RED);

        // Receipt Text Area
        receiptTextArea = new JTextArea();
        receiptTextArea.setBounds(30, 280, 740, 200);
        receiptTextArea.setEditable(false);
        receiptTextArea.setBorder(FIELD_BORDER);
        add(receiptTextArea);
    }

    private void createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setForeground(TEXT_COLOR); // Black text color for visibility
        label.setBounds(x, y, 150, 30);
        add(label);
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 200, 30);
        textField.setBorder(FIELD_BORDER);
        add(textField);
        return textField;
    }

    private JButton createButton(String text, int x, int y, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 40);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE); // White text color for contrast on colored buttons
        button.setBorder(FIELD_BORDER);
        button.addActionListener(this);
        add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == addItemButton) {
            addItem();
        } else if (source == printButton) {
            printReceipt();
        } else if (source == clearButton) {
            clearFields();
        } else if (source == backButton) {
            dispose();
            new Dashboard("Store Manager").setVisible(true);
        }
    }

    private void addItem() {
        try {
            String itemName = itemField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            double total = price * quantity;

            totalField.setText(String.format("%.2f", total));
            receiptTextArea.append("Item: " + itemName + " | Price: " + price + " | Quantity: " + quantity + " | Total: " + total + "\n");
            clearTextFields(itemField, priceField, quantityField);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid price and quantity.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void printReceipt() {
        try {
            boolean printed = receiptTextArea.print();
            if (printed) {
                JOptionPane.showMessageDialog(null, "Receipt Printed Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Printing Cancelled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        receiptTextArea.setText("");
        clearTextFields(customerNameField, itemField, priceField, quantityField, totalField);
    }

    private void clearTextFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Receipt_Page().setVisible(true);
        });
    }
}
