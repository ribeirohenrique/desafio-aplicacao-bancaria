package entities;

import java.util.List;
import java.util.stream.Collectors;

public class Account {
    int accountNumber;
    int agencyNumber;
    String clientName;
    double accountBalance;
    double accountLimit;
    String accountType;

    public Account() {
    }

    public Account(int accountNumber, int agencyNumber, String clientName, double accountLimit, String accountType) {
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.clientName = clientName;
        this.accountLimit = accountLimit;
        this.accountType = accountType;
        this.accountBalance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getAgencyNumber() {
        return agencyNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public double getAccountLimit() {
        return accountLimit;
    }

    public String getAccountType() {
        return accountType;
    }

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public void withdraw(double amount) {
        accountBalance -= amount;
    }

    public void changeAccountLimit(double amount) {
        accountLimit = amount;
    }

    public void moneyTransfer(List<Account> accountList, int accountNumber, int agencyNumber, double amount) {

        //tentar fazer uma lista que busque por accountnumber e agencynumber, se não existir, lancar um erro, se existir, efetuar a transferencia.
        List<Account> accountFound = accountList.stream().filter(account -> account.getAccountNumber() == accountNumber && account.getAgencyNumber() == agencyNumber).collect(Collectors.toList());
        for (Account a : accountFound) {
            System.out.println(a);
        }
        //accountFound.set()


    }
}
