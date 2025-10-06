package Bank_Managment_System;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String holderName;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(Transaction.Type.DEPOSIT, amount));
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction(Transaction.Type.WITHDRAWAL, amount));
            return true;
        }
        return false;
    }

    public void printStatement() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + holderName);
        System.out.println("Current Balance: " + balance);
        System.out.println("Transaction History:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("----------------------------");
    }
}
