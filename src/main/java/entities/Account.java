package entities;

import java.util.List;

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

//        private Account findAccount(int accountToFind, List<Account> accountList) {
//        List<Account> accountToDeposit;
//        try {
//            if (accountList.stream().filter(produto -> produto.getAccountNumber() == accountToFind).toList().isEmpty()) {
//                throw new BankingExceptions("Conta bancária não encontrada");
//            }
//            accountToDeposit = accountList.stream().filter(produto -> produto.getAccountNumber() == accountToFind).toList();
//            return accountToDeposit.get(0);
//        } catch (BankingExceptions exceptions) {
//            System.out.println(exceptions.getMessage());
//        }
//        return null;
//    }

    private static Account findAccount(int accountToFind, List<Account> accountList) {
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountToFind) {
                return account;
            }
        }
        return null; // Retorna null se a conta não for encontrada
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

    public double deposit(int accountNumber, double amount, List<Account> accountList) {
        Account account = findAccount(accountNumber, accountList);
        try {
            if (account == null) {
                throw new BankingExceptions("Conta não encontrada");
            }
            account.accountBalance += amount;
        } catch (BankingExceptions exceptions) {
            exceptions.getMessage();
        }

        return account.accountBalance;
    }

    public void withdraw(double amount) {
        accountBalance -= amount;
    }

    public String viewBalance(int accountNumber, List<Account> accountList) {
        Account account = findAccount(accountNumber, accountList);
        try {
            if (account == null) {
                throw new BankingExceptions("Conta não encontrada");
            }
        } catch (BankingExceptions exceptions) {
            exceptions.getMessage();
        }
        return account.toString();
    }

    public void changeAccountLimit(double amount) {
        accountLimit = amount;
    }

    public void moneyTransfer(List<Account> accountList, int accountNumber, int agencyNumber, double amount) {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dados da conta bancária:");
        sb.append("Account Number: ").append(getAccountNumber());
        sb.append("Account Agency: ").append(getAgencyNumber());
        sb.append("Account Balance: ").append(String.format("%.2f", getAccountBalance()));
        sb.append("Account Limit: ").append(String.format("%.2f", getAccountLimit()));
        sb.append("Account Type: ").append(getAccountType().toUpperCase());
        return sb.toString();
    }
}
