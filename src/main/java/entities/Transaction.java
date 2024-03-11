package entities;

public class Transaction {
    private String date;
    private int accountNumber;
    private String description;

    public Transaction(String date, int accountNumber, String description) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getDescription() {
        return description;
    }
}