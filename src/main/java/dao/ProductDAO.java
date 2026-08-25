package dao;

import java.sql.Connection;
import util.DBConnection;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {

    // ADD PRODUCT>>>

    public boolean addProduct(Product product) {

        String sql = "INSERT INTO products (name, category, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStock());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product added successfully!");
                return true;
            } else {
                System.out.println("No row affected!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // UPDATE PRODUCT >>>

    public boolean updateProduct(Product product) {

        String sql = "UPDATE products SET name = ?, category = ?, price = ?, stock = ? WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStock());
            statement.setInt(5, product.getProductId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product updated successfully");
                return true;
            } else {
                System.out.println("No rows affected!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE PRODUCT >>>

    public boolean deleteProduct(Product product) {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getProductId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product deleted.");
                return true;
            } else {
                System.out.println("No rows affected!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
