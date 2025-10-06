package Bank_Managment_System;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        if (!accounts.containsKey(account.getAccountNumber())) {
            accounts.put(account.getAccountNumber(), account);
        } else {
            System.out.println("Account with this number already exists.");
        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void performDeposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performWithdrawal(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void printAccountStatement(String accountNumber) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.printStatement();
        } else {
            System.out.println("Account not found.");
        }
    }
}
