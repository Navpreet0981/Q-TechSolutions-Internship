package ECommerce_Cart_Simulation;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Class to represent product with name, price, and quantity
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int qty) {
        this.quantity = qty;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}

// Cart class to hold list of products selected
class Cart {
    private ArrayList<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product p, int qty) {
        p.setQuantity(qty);
        products.add(p);
    }

    public double getFinalBill() {
        double total = 0;
        for (Product p : products) {
            total += p.getTotalPrice();
        }
        return total;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}

public class EcommerceCartSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample product catalogue
        Product[] catalogue = {
                new Product("Laptop", 50000),
                new Product("Smartphone", 20000),
                new Product("Headphones", 1500),
                new Product("Keyboard", 700),
                new Product("Mouse", 500)
        };

        Cart cart = new Cart();

        System.out.println("Welcome to E-commerce Cart Simulation");
        System.out.println("Product List:");
        for (int i = 0; i < catalogue.length; i++) {
            System.out.printf("%d. %s - ₹%.2f\n", (i + 1), catalogue[i].getName(), catalogue[i].getPrice());
        }

        while (true) {
            System.out.print("Enter product number to add to cart (0 to finish): ");
            int choice = scanner.nextInt();
            if (choice == 0) break;

            if (choice < 1 || choice > catalogue.length) {
                System.out.println("Invalid product number. Try again.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            if (qty <= 0) {
                System.out.println("Quantity must be positive. Try again.");
                continue;
            }

            Product chosenProduct = catalogue[choice - 1];
            cart.addProduct(new Product(chosenProduct.getName(), chosenProduct.getPrice()), qty);
            System.out.println(qty + " " + chosenProduct.getName() + "(s) added to cart.");
        }

        System.out.println("\n--- Final Bill ---");
        System.out.println("Product\tQuantity\tPrice per unit\tTotal");
        for (Product p : cart.getProducts()) {
            System.out.printf("%s\t%d\t\t₹%.2f\t\t₹%.2f\n", p.getName(), p.getQuantity(), p.getPrice(), p.getTotalPrice());
        }
        System.out.printf("Final Amount: ₹%.2f\n", cart.getFinalBill());

        // Saving bill to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FinalBill.txt"))) {
            writer.write("----- Final Bill -----\n");
            writer.write("Product\tQuantity\tPrice per unit\tTotal\n");
            for (Product p : cart.getProducts()) {
                writer.write(String.format("%s\t%d\t\t₹%.2f\t\t₹%.2f\n", p.getName(), p.getQuantity(), p.getPrice(), p.getTotalPrice()));
            }
            writer.write(String.format("Final Amount: ₹%.2f\n", cart.getFinalBill()));
            System.out.println("Final bill saved to FinalBill.txt");
        } catch (IOException e) {
            System.out.println("Error saving final bill: " + e.getMessage());
        }

        scanner.close();
    }
}
