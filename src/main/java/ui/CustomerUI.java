package ui;

import javax.swing.*;

import model.Customer;
import service.CustomerService;

import java.awt.*;

public class CustomerUI extends JFrame {

    private JTextField nameField;
    private JTextField phoneField;
    private JButton addButton;
    private CustomerService customerService;
    private int customerId=1;

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

        addButton = new JButton("Add Customer");

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(phoneLabel);
        panel.add(phoneField);

        panel.add(new JLabel());
        panel.add(addButton);

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

            Customer customer = new Customer(customerId, name, phone); 
            customerService.addCustomer(customer);
            customerId++;
    
           JOptionPane.showMessageDialog(this,"Customer added successfully!\nCustomer ID: " + customer.getCustomerId());

        });

        // Add panel to window
        add(panel);

        setVisible(true);
    }
}