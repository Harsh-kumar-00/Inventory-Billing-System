package ui;

import javax.swing.*;

import model.Customer;
import service.CustomerService;

import java.awt.*;

public class CustomerUI extends JFrame {

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField customerIdField;
    private JButton addButton;
    private JButton findButton;
    private JButton viewAllButton;
    private JButton updateButton;
    private JButton deleteButton;
    private CustomerService customerService;

    public CustomerUI() {
        customerService = new CustomerService();
        setTitle("Customer Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(20);

        JLabel customerIdLabel=new JLabel("Customer ID:");
        customerIdField =new JTextField(20);

        findButton =new JButton("Find Customer");
        addButton = new JButton("Add Customer");
        viewAllButton = new JButton("View All Customers");
        updateButton=new JButton("Update Customer");
        deleteButton=new JButton("Delete Customer");
        // Create panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customer ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(customerIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(customerIdField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(findButton, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(nameField, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(phoneField, gbc);

        // Add Customer button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        // Update Customer button
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(updateButton, gbc);

        //Delete Customer button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(deleteButton, gbc);


        // View All Customers button
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(viewAllButton, gbc);

        addButton.addActionListener(e -> {

            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                this,
                "Please enter name and phone."
                );

                return;
            }

            Customer customer = new Customer(0, name, phone); 
            customerService.addCustomer(customer);
            
    
           JOptionPane.showMessageDialog(this,"Customer added successfully!\nCustomer ID: " + customer.getCustomerId());

        });

        findButton.addActionListener(e -> {

            String idText = customerIdField.getText().trim();

            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(
                  this,
                  "Please enter Customer ID."
                );
                return;
            }

            try {
               int customerId = Integer.parseInt(idText);

                Customer customer = customerService.findCustomer(customerId);

                if (customer != null) {
                   nameField.setText(customer.getName());
                   phoneField.setText(customer.getPhone());

                   JOptionPane.showMessageDialog(
                      this,
                       "Customer found!"
                    );
                } else {
                    JOptionPane.showMessageDialog(
                       this,
                       "Customer not found."
                    );
                }

            } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                  this,
                    "Customer ID must be a number."
                );
            }
        });

        updateButton.addActionListener(e -> {

            String idText = customerIdField.getText().trim();
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            if (idText.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID, name and phone."
                );
                return;
            }

            try {
                int customerId = Integer.parseInt(idText);

                Customer customer = new Customer(customerId, name, phone);

                customerService.updateCustomer(customer);

                JOptionPane.showMessageDialog(
                    this,
                    "Customer updated successfully!"
                );

            } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number."
                );
            }
        });

        deleteButton.addActionListener(e -> {

            String idText = customerIdField.getText().trim();

            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                     "Please enter Customer ID."
                );
                return;
            }

            try {
                 int customerId = Integer.parseInt(idText);

                int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this customer?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {

                    customerService.deleteCustomer(customerId);

                    JOptionPane.showMessageDialog(
                        this,
                        "Customer deleted successfully!"
                    );

                    customerIdField.setText("");
                    nameField.setText("");
                    phoneField.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number."
                );
            }
        });

        viewAllButton.addActionListener(e -> {

            java.util.List<Customer> customers = customerService.getAllCustomers();

            if (customers.isEmpty()) {
                 JOptionPane.showMessageDialog(
                 this,
                 "No customers found."
                );
                return;
            }

            StringBuilder result = new StringBuilder();

            for (Customer customer : customers) {
                result.append("ID: ")
                      .append(customer.getCustomerId())
                      .append(" | Name: ")
                      .append(customer.getName())
                      .append(" | Phone: ")
                      .append(customer.getPhone())
                      .append("\n");
            }

            JOptionPane.showMessageDialog(
                this,
                result.toString(),
                "All Customers",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        // Add panel to window
        add(panel);

        setVisible(true);
    }
}