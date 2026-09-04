package service;

import dao.ProductDAO;
import model.Product;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    public boolean addProduct(Product product) { // this validates if the product added has all its attributes filled
 // correctly

        if (product.getName() == null || product.getName().isBlank()) {
            System.out.println("Product name cannot be empty.");
            return false;
        }

        if (product.getPrice() <= 0) {
            System.out.println("Price must be greater than zero.");
            return false;
        }

        if(product.getStock() < 0) {
            System.out.println("Stock cannot be negative");
            return false;
        }

        return productDAO.addProduct(product);

    }

    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public boolean updateProduct(Product product) {
        if(product.getName() == null || product.getName().isBlank()) {
            System.out.println("Product name cannot be empty.");
            return false;
        }
        if(product.getPrice() <= 0) {
            System.out.println("Price must be greater than zero.");
            return false;
        }
        if(product.getStock() < 0) {
            System.out.println("Stock cannot be negative.");
            return false;
        }

        return productDAO.updateProduct(product);
    }

    public boolean deleteProduct(Product product) {
        return productDAO.deleteProduct(product);
    }

    // CHECK STOCK >>>

    public int checkStock(int productId) {
        Product product = productDAO.getProductById(productId);

        if(product == null) {
            return -1;
        }
        return product.getStock();
    }

    // UPDATE STOCK >>>

    public boolean updateStock(int productId, int newStock) {
        if(newStock < 0) {
            System.out.println("Stock cannot be negative.");
            return false;
        }
        return productDAO.updateStock(productId, newStock);
    }

    public boolean isLowStock(int productId) {
        Product product = productDAO.getProductById(productId);

        if(product == null) {
            return false;
        }

        return product.getStock() < 5;
    }

    public boolean isOutOfStock(int productId) {
        Product product = productDAO.getProductById(productId);

        if(product == null) {
            return false;
        }
        return product.getStock() == 0;
    }

}