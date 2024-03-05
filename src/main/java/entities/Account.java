package entities;

import java.util.List;

public class Account {
    int accountNumber;
    int agencyNumber;
    String clientName;
    double accountBalance;
    double accountLimit;
    String accountType;

    public Account(int accountNumber, String clientName, double accountLimit, String accountType) {
        this.accountNumber = accountNumber;
        this.agencyNumber = 1;
        this.clientName = clientName;
        this.accountLimit = accountLimit;
        this.accountType = accountType;
        this.accountBalance = 0;
    }

    public static Account createNewAccount(Account account, List<Account> accountList) {
        Account createAccount = findAccount(account.getAccountNumber(), accountList);
        try {
            if (createAccount != null) {
                throw new BankingExceptions("Erro: O número da conta já existe.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
        return account;
    }

    // Retorna null se a conta não for encontrada
    // TODO: Buscar forma de melhorar o acoplamento
    public static Account findAccount(int accountToFind, List<Account> accountList) {
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountToFind) {
                return account;
            }
        }
        return null;
    }

    public void viewBalance(int accountNumber, List<Account> accountList) {
        Account account = findAccount(accountNumber, accountList);
        try {
            if (account != null) {
                System.out.println(account);
            } else {
                throw new BankingExceptions("Não foi possível visualizar o saldo.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
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

    public void depositAccount(int accountNumber, double amount, List<Account> accountList) {
        Account account = findAccount(accountNumber, accountList);
        try {
            if (account != null) {
                account.accountBalance += amount;
                System.out.println("Depósito realizado com sucesso!");
                System.out.println("Saldo atual: " + account.getAccountBalance());
            } else {
                throw new BankingExceptions("Erro: Não foi possível processar o depósito.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    public void withdrawAccount(int accountNumber, double amount, List<Account> accountList) {
        Account account = findAccount(accountNumber, accountList);
        try {
            if (account != null) {
                if (account.getAccountBalance() < amount) {
                    throw new BankingExceptions("Erro: O valor a ser sacado é maior que o saldo atual.");
                }
                account.accountBalance -= amount;
                System.out.println("Saque realizado com sucesso!");
                System.out.println("Saldo atual: " + account.getAccountBalance());
            } else {
                throw new BankingExceptions("Erro: Não foi possível processar o saque.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }

    }

    public void transferBetweenAccounts(int accountNumberSender, int accountNumberReceiver, double amount, List<Account> accountList) {
        Account accountSender = findAccount(accountNumberSender, accountList);
        Account accountReceiver = findAccount(accountNumberReceiver, accountList);
        try {
            if (accountSender != null || accountReceiver != null) {
                if (accountSender.getAccountBalance() < amount) {
                    throw new BankingExceptions("Erro: O valor a ser transferido é maior que o saldo atual.");
                }
                //primeiro subtrai do Sender
                accountSender.withdrawAccount(accountNumberSender, amount, accountList);
                //depois deposita no Receiver
                accountReceiver.depositAccount(accountNumberReceiver, amount, accountList);
                System.out.println("Transferência realizado com sucesso!");
                System.out.println("Saldo atual: " + accountSender.getAccountBalance());
            } else {
                throw new BankingExceptions("Erro: Não foi possível processar a transferência.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    public void changeAccountLimit(int accountNumber, double newLimit, List<Account> accountList) {
        Account account = findAccount(accountNumber, accountList);
        try {
            if (account != null) {
                account.accountLimit = newLimit;
                System.out.println("Limite alterado com sucesso!");
                System.out.println("Limite atual: " + account.getAccountLimit());
            } else {
                throw new BankingExceptions("Erro: Não foi possível processar a alteração de limite da conta.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    @Override
    public String toString() {
        return
                "Dados da conta bancária: \n" +
                        "Account Name: " + getClientName() + "\n" +
                        "Account Number: " + getAccountNumber() + "\n" +
                        "Account Agency: " + getAgencyNumber() + "\n" +
                        "Account Balance: " + String.format("%.2f", getAccountBalance()) + "\n" +
                        "Account Limit: " + String.format("%.2f", getAccountLimit()) + "\n" +
                        "Account Type: " + getAccountType() + "\n" +
                        "============================================\n";
    }
}
