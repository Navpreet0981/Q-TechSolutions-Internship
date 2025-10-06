package Bank_Managment_System;

import java.util.Date;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAWAL }

    private Type type;
    private double amount;
    private Date date;

    public Transaction(Type type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return date + " - " + type + ": " + amount;
    }
}
