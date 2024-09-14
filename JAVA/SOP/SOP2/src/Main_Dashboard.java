// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main_Dashboard extends JFrame implements ActionListener {
   private JButton inventoryButton;
   private JButton salesRecordsButton;
   private JButton hrButton;
   private JButton supplierButton;
   private JButton backButton;
   private JPanel leftPanel;
   private JPanel rightPanel;
   private JLabel companyNameLabel;
   private JLabel copyrightLabel;
   private String userRole;

   public Main_Dashboard(String var1) {
      this.userRole = var1;
      this.initComponents();
   }

   private void initComponents() {
      this.setDefaultCloseOperation(3);
      this.setTitle("MAIN DASHBOARD");
      this.setPreferredSize(new Dimension(800, 500));
      JPanel var1 = new JPanel((LayoutManager)null);
      var1.setBackground(Color.WHITE);
      var1.setPreferredSize(new Dimension(800, 500));
      this.rightPanel = this.createRightPanel();
      var1.add(this.rightPanel);
      this.rightPanel.setBounds(400, 0, 400, 500);
      this.leftPanel = this.createLeftPanel();
      var1.add(this.leftPanel);
      this.leftPanel.setBounds(0, 0, 400, 500);
      this.setLayout(new BorderLayout());
      this.add(var1, "Center");
      this.pack();
      this.setLocationRelativeTo((Component)null);
   }

   private JPanel createRightPanel() {
      JPanel var1 = new JPanel();
      var1.setBackground(new Color(0, 102, 102));
      var1.setLayout(new BorderLayout());
      this.companyNameLabel = new JLabel("Store Name", 0);
      this.companyNameLabel.setFont(new Font("Showcard Gothic", 1, 24));
      this.companyNameLabel.setForeground(Color.WHITE);
      this.copyrightLabel = new JLabel("\u00a9 Store Name. All rights reserved.", 0);
      this.copyrightLabel.setForeground(new Color(204, 204, 204));
      var1.add(this.companyNameLabel, "Center");
      var1.add(this.copyrightLabel, "South");
      return var1;
   }

   private JPanel createLeftPanel() {
      JPanel var1 = new JPanel();
      var1.setBackground(Color.WHITE);
      var1.setLayout(new GridBagLayout());
      GridBagConstraints var2 = new GridBagConstraints();
      var2.insets = new Insets(10, 10, 10, 10);
      var2.anchor = 17;
      var2.fill = 2;
      var2.weightx = 1.0;
      this.inventoryButton = this.createButton("Inventory Management");
      this.salesRecordsButton = this.createButton("Sales Records");
      this.hrButton = this.createButton("Human Resources");
      this.supplierButton = this.createButton("Supplier Communication");
      this.backButton = this.createButton("BACK");
      var2.gridy = 0;
      var1.add(this.inventoryButton, var2);
      var2.gridy = 1;
      var1.add(this.salesRecordsButton, var2);
      var2.gridy = 2;
      var1.add(this.hrButton, var2);
      var2.gridy = 3;
      var1.add(this.supplierButton, var2);
      var2.gridy = 4;
      var1.add(this.backButton, var2);
      if (!this.userRole.equals("Cashier") && !this.userRole.equals("Sales Assistant")) {
         if (!this.userRole.equals("Store Manager") && !this.userRole.equals("HR Manager")) {
            JOptionPane.showMessageDialog((Component)null, "Unknown role: " + this.userRole);
         }
      } else {
         this.hrButton.setEnabled(false);
         this.supplierButton.setEnabled(false);
      }

      this.inventoryButton.addActionListener(this);
      this.salesRecordsButton.addActionListener(this);
      this.hrButton.addActionListener(this);
      this.supplierButton.addActionListener(this);
      this.backButton.addActionListener(this);
      return var1;
   }

   private JButton createButton(String var1) {
      JButton var2 = new JButton(var1);
      var2.setFont(new Font("Segoe UI", 0, 14));
      var2.setBackground(new Color(0, 102, 102));
      var2.setForeground(Color.WHITE);
      return var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (var1.getSource() == this.backButton) {
         this.dispose();
         (new Login_Interface()).setVisible(true);
      } else if (var1.getSource() == this.inventoryButton) {
         this.dispose();
         (new Inventory_Interface(this.userRole)).setVisible(true);
      } else if (var1.getSource() == this.salesRecordsButton) {
         this.dispose();
         (new Sales_Record_Interface(this.userRole)).setVisible(true);
      } else if (var1.getSource() == this.hrButton) {
         this.dispose();
         (new Human_Resource_Interface(this.userRole)).setVisible(true);
      } else if (var1.getSource() == this.supplierButton) {
         this.dispose();
         (new Supplier_Communication_Interface(this.userRole)).setVisible(true);
      }

   }

   public static void main(String[] var0) {
      SwingUtilities.invokeLater(() -> {
         (new Main_Dashboard("Store Manager")).setVisible(true);
      });
   }
}
