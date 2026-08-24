package dao;

import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class CustomerDAO {

    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {

            if (customers.get(i).getCustomerId() == customer.getCustomerId()) {
                customers.set(i, customer);
                return;
            }
        }
    }

    public void deleteCustomer(int customerId) {
        customers.removeIf(customer -> customer.getCustomerId() == customerId);
    }

    public Customer findCustomer(int customerId) {

        for (Customer customer : customers) {

            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}