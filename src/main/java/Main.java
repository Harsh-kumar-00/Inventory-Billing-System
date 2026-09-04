import java.util.Scanner;
import model.Product;
import service.ProductService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();

        int choice;

        do {
            System.out.println("\n===== INVENTORY & BILLING SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Product");
            System.out.println("3. View All Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter product name: ");
                String name = scanner.next();

                System.out.print("Enter category: ");
                String category = scanner.next();

                System.out.print("Enter price: ");
                double price = scanner.nextDouble();

                System.out.print("Enter stock: ");
                int stock = scanner.nextInt();

                Product product = new Product(0, name, category, price, stock);

                productService.addProduct(product);
            }

            if (choice == 2) {
                System.out.print("Enter productId: ");
                int productId = scanner.nextInt();

                Product product = productService.getProductById(productId);

                if (product != null) {
                    System.out.println(
                            product.getProductId() + " | " +
                                    product.getName() + " | " +
                                    product.getCategory() + " | " +
                                    product.getPrice() + " | " +
                                    product.getStock());
                }
            }

            if (choice == 3) {
                List<Product> products = productService.getAllProducts();
                for (Product product : products) {
                    System.out.println(
                            product.getProductId() + " | " +
                                    product.getName() + " | " +
                                    product.getCategory() + " | " +
                                    product.getPrice() + " | " +
                                    product.getStock());
                }
            }

            if (choice == 4) {
                System.out.print("Enter product ID to update: ");
                int productId = scanner.nextInt();

                System.out.print("Enter new product name: ");
                String name = scanner.next();

                System.out.print("Enter new category: ");
                String category = scanner.next();

                System.out.print("Enter new price: ");
                double price = scanner.nextDouble();

                System.out.print("Enter new stock: ");
                int stock = scanner.nextInt();

                Product product = new Product(productId, name, category, price, stock);

                productService.updateProduct(product);
            }

            if (choice == 5) {
                System.out.print("Enter product ID to delete: ");
                int productId = scanner.nextInt();

                Product product = new Product(productId, "", "", 0, 0);

                productService.deleteProduct(product);
            }

        } while (choice != 6);

    }
}