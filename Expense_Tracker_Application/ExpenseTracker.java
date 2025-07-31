package Expense_Tracker_Application;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Transaction class (implements Serializable for persistence)
class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    double amount;
    String description;
    boolean isExpense;

    public Transaction(double amount, String description, boolean isExpense) {
        this.amount = amount;
        this.description = description;
        this.isExpense = isExpense;
    }

    @Override
    public String toString() {
        String type = isExpense ? "Expense" : "Income";
        return String.format("%s: %s - $%.2f", type, description, amount);
    }
}

public class ExpenseTracker {
    private static final String DATA_FILE = "Expense_Tracker_Application/transactions.ser";
    private List<Transaction> transactions;
    private Scanner scanner;

    public ExpenseTracker() {
        transactions = loadTransactions();
        if (transactions == null) transactions = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    private List<Transaction> loadTransactions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (List<Transaction>) ois.readObject();
        } catch (Exception e) {
            // File not found or error, return null to start fresh
            return null;
        }
    }

    private void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    private void addTransaction(boolean isExpense) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        transactions.add(new Transaction(amount, desc, isExpense));
        System.out.println((isExpense ? "Expense" : "Income") + " added successfully.");
    }

    private void printSummary() {
        double totalIncome = 0;
        double totalExpense = 0;
        System.out.println("\nTransactions:");
        for (Transaction t : transactions) {
            System.out.println(t);
            if (t.isExpense) totalExpense += t.amount;
            else totalIncome += t.amount;
        }
        System.out.printf("\nTotal Income: $%.2f\n", totalIncome);
        System.out.printf("Total Expense: $%.2f\n", totalExpense);
        System.out.printf("Balance: $%.2f\n", totalIncome - totalExpense);
    }

    public void start() {
        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Show Summary");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTransaction(false);
                case 2 -> addTransaction(true);
                case 3 -> printSummary();
                case 4 -> {
                    saveTransactions();
                    System.out.println("Data saved. Exiting.");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        ExpenseTracker app = new ExpenseTracker();
        app.start();
    }
}
