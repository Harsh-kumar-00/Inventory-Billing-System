import java.util.Scanner;
import model.Product;

public class Main {

    public static void main(String[] args) {

        System.out.println("Inventory & Billing System Started!");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Product ID:");
        int productId = sc.nextInt();

        sc.nextLine(); // consume leftover newline

        System.out.println("Enter Product Name:");
        String name = sc.nextLine();

        System.out.println("Enter Category:");
        String category = sc.nextLine();

        System.out.println("Enter Price:");
        double price = sc.nextDouble();

        System.out.println("Enter Stock:");
        int stock = sc.nextInt();

        Product pd = new Product(productId, name, category, price, stock);

        System.out.println("\nProduct Details:");
        System.out.println("ID: " + pd.getProductId());
        System.out.println("Name: " + pd.getName());
        System.out.println("Category: " + pd.getCategory());
        System.out.println("Price: " + pd.getPrice());
        System.out.println("Stock: " + pd.getStock());

        pd.setStock(15);

        System.out.println("Updated Stock: " + pd.getStock());

        sc.close();
    }
}