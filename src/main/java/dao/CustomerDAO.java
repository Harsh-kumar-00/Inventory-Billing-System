package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import util.DBConnection;

public class CustomerDAO {

    public void addCustomer(Customer customer) {

        String sql = "INSERT INTO customers (name, phone) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql,
                     java.sql.Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                customer.setCustomerId(rs.getInt(1));
            }

            System.out.println("Customer added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateCustomer(Customer customer) {

        String sql = "UPDATE customers SET name = ?, phone = ? WHERE customer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setInt(3, customer.getCustomerId());

            ps.executeUpdate();

            System.out.println("Customer updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteCustomer(int customerId) {

        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            ps.executeUpdate();

            System.out.println("Customer deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Customer findCustomer(int customerId) {

        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("name"),
                    rs.getString("phone")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customers";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Customer customer = new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("name"),
                    rs.getString("phone")
                );

                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}