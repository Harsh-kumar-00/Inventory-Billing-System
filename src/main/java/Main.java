import java.sql.Connection;
import util.DBConnection;
import dao.ProductDAO;
import model.Product;

public class Main {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Database connection failed!");
        }

        /*
         * Product product = new Product(
         * 0,
         * "Keyboard",
         * "Electronics",
         * 799.00,
         * 20);
         * 
         * ProductDAO productDAO = new ProductDAO();
         * 
         * boolean result = productDAO.addProduct(product);
         * 
         * System.out.println("Result: " + result);
         */

        /*
         * Product product = new Product(
         * 1,
         * "Keyboard",
         * "Electronics",
         * 899.00,
         * 15);
         * 
         * ProductDAO productDAO = new ProductDAO();
         * 
         * boolean result = productDAO.updateProduct(product);
         * 
         * System.out.println("Result: " + result);
         */

        Product product = new Product(
                1,
                "Keyboard",
                "Electronics",
                899.00,
                15);

        ProductDAO productDAO = new ProductDAO();

        boolean result = productDAO.deleteProduct(product);

        System.out.println("Result: " + result);
    }
}