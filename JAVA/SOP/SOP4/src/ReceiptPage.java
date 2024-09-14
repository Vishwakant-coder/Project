import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReceiptPage extends JFrame implements ActionListener {

    private JTextArea receiptTextArea;
    private JTextField customerNameField, itemField, priceField, quantityField, totalField;
    private JButton addItemButton, printButton, clearButton, backButton;

    public ReceiptPage() {
        setTitle("BF POS System - Receipt Printing");
        setLayout(null);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background Color
        getContentPane().setBackground(new Color(60, 63, 65));

        // Name Label and Text Field
        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        customerNameLabel.setForeground(Color.WHITE);
        customerNameLabel.setBounds(30, 20, 150, 30);
        add(customerNameLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(180, 20, 200, 30);
        customerNameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(customerNameField);

        // Item Label and Text Field
        JLabel itemLabel = new JLabel("Item:");
        itemLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        itemLabel.setForeground(Color.WHITE);
        itemLabel.setBounds(30, 70, 150, 30);
        add(itemLabel);

        itemField = new JTextField();
        itemField.setBounds(180, 70, 200, 30);
        itemField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(itemField);

        // Price Label and Text Field
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setBounds(30, 120, 150, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(180, 120, 200, 30);
        priceField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(priceField);

        // Quantity Label and Text Field
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        quantityLabel.setForeground(Color.WHITE);
        quantityLabel.setBounds(30, 170, 150, 30);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(180, 170, 200, 30);
        quantityField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(quantityField);

        // Total Label and Text Field
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setBounds(30, 220, 150, 30);
        add(totalLabel);

        totalField = new JTextField();
        totalField.setBounds(180, 220, 200, 30);
        totalField.setEditable(false);
        totalField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(totalField);

        // Add Item Button
        addItemButton = new JButton("Add Item");
        addItemButton.setBounds(400, 70, 150, 40); // Adjusted size and position
        addItemButton.addActionListener(this);
        add(addItemButton);

        // Receipt Text Area
        receiptTextArea = new JTextArea();
        receiptTextArea.setBounds(30, 280, 740, 200); // Adjusted width to fit within the frame
        receiptTextArea.setEditable(false);
        receiptTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(receiptTextArea);

        // Print Button
        printButton = new JButton("Print");
        printButton.setBounds(150, 500, 150, 40); // Adjusted size and position
        printButton.addActionListener(this);
        add(printButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(325, 500, 150, 40); // Adjusted size and position
        clearButton.addActionListener(this);
        add(clearButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(500, 500, 150, 40); // Adjusted size and position
        backButton.addActionListener(this);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addItemButton) {
            try {
                String itemName = itemField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                double total = price * quantity;

                totalField.setText(String.format("%.2f", total));

                // Add item details to receipt
                receiptTextArea.append("Item: " + itemName + " | Price: " + price + " | Quantity: " + quantity + " | Total: " + total + "\n");

                // Clear fields after adding
                itemField.setText("");
                priceField.setText("");
                quantityField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid price and quantity.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == printButton) {
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
        } else if (ae.getSource() == clearButton) {
            receiptTextArea.setText("");
            customerNameField.setText("");
            itemField.setText("");
            priceField.setText("");
            quantityField.setText("");
            totalField.setText("");
        } else if (ae.getSource() == backButton) {
            this.dispose();
            // Replace this with the previous frame call you have
            new Main_Dashboard("Store Manager").setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReceiptPage().setVisible(true);
        });
    }
}
