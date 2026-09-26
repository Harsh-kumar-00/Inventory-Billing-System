import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Bill;
import model.BillItem;
import model.Product;
import service.BillingService;
import service.ProductService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ProductService productService = new ProductService();
        BillingService billingService = new BillingService();

        int choice;

        do {
            System.out.println("\n===== INVENTORY & BILLING SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Product");
            System.out.println("3. View All Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");
            System.out.println("7. Create Bill");

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

            } else if (choice == 2) {

                System.out.print("Enter product ID: ");
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

            } else if (choice == 3) {

                List<Product> products = productService.getAllProducts();

                for (Product product : products) {
                    System.out.println(
                            product.getProductId() + " | " +
                                    product.getName() + " | " +
                                    product.getCategory() + " | " +
                                    product.getPrice() + " | " +
                                    product.getStock());
                }

            } else if (choice == 4) {

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

            } else if (choice == 5) {

                System.out.print("Enter product ID to delete: ");
                int productId = scanner.nextInt();

                Product product = new Product(productId, "", "", 0, 0);

                productService.deleteProduct(product);

            } else if (choice == 7) {

                List<BillItem> items = new ArrayList<>();
                String more = "n";

                do {
                    System.out.print("Enter the product ID: ");
                    int productId = scanner.nextInt();

                    Product product = productService.getProductById(productId);

                    if (product != null) {

                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();

                        if (quantity <= 0) {
                            System.out.println(
                                    "Quantity must be greater than zero.");
                        } else if (quantity > product.getStock()) {
                            System.out.println("Insufficient stock.");
                        } else {
                            BillItem item = new BillItem(product, quantity);

                            items.add(item);
                        }
                    }

                    System.out.print("Add another product? (y/n): ");
                    more = scanner.next();

                } while (more.equalsIgnoreCase("y"));

                if (!items.isEmpty()) {

                    Bill bill = billingService.createBill(items);

                    for (BillItem item : items) {

                        Product product = item.getProduct();

                        int newStock = product.getStock() - item.getQuantity();

                        productService.updateStock(
                                product.getProductId(),
                                newStock);
                    }

                    System.out.println("\n===== BILL =====");

                    System.out.printf(
                            "Subtotal: Rs.%.2f%n",
                            bill.getSubtotal());

                    System.out.printf(
                            "Discount: Rs.%.2f%n",
                            bill.getDiscount());

                    System.out.printf(
                            "Tax: Rs.%.2f%n",
                            bill.getTax());

                    System.out.printf(
                            "Grand Total: Rs.%.2f%n",
                            bill.getGrandTotal());
                }

            } else if (choice != 6) {

                System.out.println("Invalid choice.");

            }

        } while (choice != 6);

        scanner.close();
    }
}