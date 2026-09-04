package dao;

import java.sql.Connection;
import util.DBConnection;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

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

    // GET ELEMENT BY ID >>>

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);

                ResultSet resultSet = statement.executeQuery();

                // resultSet.next() return boolean
                if(resultSet.next()) {
                    int id = resultSet.getInt("product_id");
                    String name = resultSet.getString("name");
                    String category = resultSet.getString("category");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");
                    
                    Product product = new Product(id, name, category, price, stock);

                    return product;
                } else {
                    System.out.println("Product not found!");
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    // GET ALL PRODUCTS >>>

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    int id = resultSet.getInt("product_id");
                    String name = resultSet.getString("name");
                    String category = resultSet.getString("category");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");

                    Product product = new Product(id, name, category, price, stock);

                    products.add(product);
                }
             } catch (SQLException e) {
                e.printStackTrace();
                return null;
             }

             return products;
    }

    // UPDATE STOCK >>>

    public boolean updateStock(int productId, int newStock) {
        String sql = "UPDATE products SET stock = ?  WHERE product_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement  statement = connection.prepareStatement(sql)) {
                statement.setInt(1, newStock);
                statement.setInt(2, productId);

                int rowAffected = statement.executeUpdate();

                if(rowAffected > 0) {
                    System.out.println("Stock updated successfully!");
                    return true;
                }
                else {
                    System.out.println("Product not found!");
                    return false;
                }

             }
             catch (SQLException e) {
                e.printStackTrace();
                return false;
             }
    }

}
