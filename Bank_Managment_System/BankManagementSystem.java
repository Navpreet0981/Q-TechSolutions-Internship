package Bank_Managment_System;

import java.util.Scanner;

public class BankManagementSystem {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Bank Management System");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account Statement");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter Holder Name: ");
                    String holder = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double initBal = scanner.nextDouble();
                    scanner.nextLine();
                    Account newAccount = new Account(accNum, holder, initBal);
                    bank.addAccount(newAccount);
                    System.out.println("Account created successfully!");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNum = scanner.nextLine();
                    System.out.print("Enter Deposit Amount: ");
                    double depAmount = scanner.nextDouble();
                    scanner.nextLine();
                    bank.performDeposit(accNum, depAmount);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accNum = scanner.nextLine();
                    System.out.print("Enter Withdrawal Amount: ");
                    double withAmount = scanner.nextDouble();
                    scanner.nextLine();
                    bank.performWithdrawal(accNum, withAmount);
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    accNum = scanner.nextLine();
                    bank.printAccountStatement(accNum);
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
