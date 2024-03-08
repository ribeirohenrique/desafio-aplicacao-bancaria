package application;

import entities.Account;
import entities.exceptions.BankingExceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static entities.Account.findAccount;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============================================");
        System.out.println("             APLICAÇÃO BANCÁRIA             ");
        System.out.println("============================================");
        System.out.println();
        List<Account> accountList = new ArrayList<>();
        int options = 0;
        while (options != 8) {
            screenMenu();
            System.out.print("Digite uma opção para acessar: ");
            try {
                options = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                switch (options) {
                    case 1:
                        Account account = createNewAccount(accountList);
                        accountList.add(account);
                        break;
                    case 2:
                        withdrawAccount(accountList);
                        break;
                    case 3:
                        depositAccount(accountList);
                        break;
                    case 4:
                        transferBetweenAccounts(accountList);
                        break;
                    case 5:
                        viewBalance(accountList);
                        break;
                    case 6:
                        changeAccountLimit(accountList);
                        break;
                    case 7:
                        readTransactionHistory(accountList, scanner);
                        break;
                    case 8:
                        System.out.println("Muito obrigado por confiar em nosso banco! Volte sempre :-)");
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha novamente.");
                        break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite novamente.");
                scanner.nextLine(); // Limpar o buffer
            }
        }
        scanner.close();
    }

    private static int checkAccountNumber(Scanner scanner, List<Account> accountList) throws BankingExceptions {
        if (accountList.isEmpty()) {
            throw new BankingExceptions("Não há contas cadastradas até o momento");
        }
        System.out.print("Digite o número da conta: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();
        return accountNumber;
    }

    private static Account createNewAccount(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("          Cadastro de novo Cliente:         ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número da conta: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do cliente: ");
        String clientName = scanner.nextLine();

        System.out.print("Digite o limite da conta: ");
        double accountLimit = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o tipo de conta bancária (CC/CP): ");
        String accountType = scanner.nextLine().toUpperCase();

        System.out.println("Conta criada com sucesso!");
        System.out.println();

        return Account.createNewAccount(new Account(accountNumber, clientName, accountLimit, accountType), accountList);
    }

    private static void depositAccount(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("                  Depósito:                 ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        try {
            int accountNumber = checkAccountNumber(scanner, accountList);
            System.out.print("Digite o valor a ser depositado: ");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine();
            Account account = findAccount(accountNumber, accountList);
            if (account != null) {
                account.depositAccount(accountNumber, depositAmount, accountList);
                account.saveTransactionHistoryToCSV("transaction_history.csv");
            } else {
                throw new BankingExceptions("Não foi possível encontrar a conta.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    private static void readTransactionHistory(List<Account> accountList, Scanner scanner) {
        try {
            System.out.print("Digite o número da conta para visualizar o histórico de transações: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            Account account = Account.findAccount(accountNumber, accountList);
            if (account == null) {
                String filename = "transaction_history.csv";
                List<String> transactionHistory = readTransactionHistoryFromFile(filename, accountNumber);
                if (!transactionHistory.isEmpty()) {
                    System.out.println("Histórico de transações da conta " + accountNumber + ":");
                    for (String transaction : transactionHistory) {
                        System.out.println(transaction);
                    }
                } else {
                    System.out.println("Não há histórico de transações para a conta " + accountNumber);
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro: Não foi possível ler o histórico de transações");
        }
    }

    private static List<String> readTransactionHistoryFromFile(String filename, int accountNumber) throws IOException {
        List<String> transactionHistory = new ArrayList<>();
        Path path = Paths.get(filename);
        if (Files.exists(path)) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    int number = Integer.parseInt(parts[1]);
                    if (number == accountNumber) {
                        transactionHistory.add(line);
                    }
                }
            }
        }
        return transactionHistory;
    }

    private static void withdrawAccount(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("                   Saque:                   ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        try {
            int accountNumber = checkAccountNumber(scanner, accountList);
            System.out.print("Digite o valor a ser sacado: ");
            double withdrawAmount = scanner.nextDouble();
            scanner.nextLine();
            Account account = findAccount(accountNumber, accountList);
            if (account != null) {
                account.withdrawAccount(accountNumber, withdrawAmount, accountList);
                account.saveTransactionHistoryToCSV("transaction_history.csv");
            } else {
                throw new BankingExceptions("Não foi possível encontrar a conta.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    private static void transferBetweenAccounts(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("               Transferência:               ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        try {
            if (accountList.isEmpty()) {
                throw new BankingExceptions("Não há contas cadastradas até o momento");
            }
            System.out.print("Digite o número da conta remetente: ");
            int accountNumberSender = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o número da conta destinatária: ");
            int accountNumberReceiver = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o valor da transferência: ");
            int transferAmount = scanner.nextInt();
            scanner.nextLine();
            Account accountSender = findAccount(accountNumberSender, accountList);
            Account accountReceiver = findAccount(accountNumberSender, accountList);
            if (accountSender != null && accountReceiver != null) {
                accountSender.transferBetweenAccounts(accountNumberSender, accountNumberReceiver, transferAmount, accountList);
                accountSender.saveTransactionHistoryToCSV("transaction_history.csv");
            } else {
                throw new BankingExceptions("Não foi possível encontrar a conta.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    private static void changeAccountLimit(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("               Alterar limite:              ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        try {
            int accountNumber = checkAccountNumber(scanner, accountList);
            System.out.print("Digite o novo limite: ");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine();
            Account account = findAccount(accountNumber, accountList);
            if (account != null) {
                account.changeAccountLimit(accountNumber, depositAmount, accountList);
                account.saveTransactionHistoryToCSV("transaction_history.csv");
            } else {
                throw new BankingExceptions("Não foi possível encontrar a conta.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    private static void viewBalance(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("              Verificar saldo:              ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        try {
            if (accountList.isEmpty()) {
                throw new BankingExceptions("Não há contas cadastradas até o momento");
            }
            System.out.print("Digite o número da conta: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();
            Account account = findAccount(accountNumber, accountList);
            if (account != null) {
                account.viewBalance(accountNumber, accountList);
            } else {
                throw new BankingExceptions("Não foi possível encontrar a conta.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    private static void deleteFile() {
        File transactionHistory = new File("transaction_history.csv");
        try {
            if (!transactionHistory.delete()) {
                throw new BankingExceptions("Erro ao deletar o histórico de transações");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }

    }

    private static void screenMenu() {
        System.out.println("############################################");
        System.out.println("               MENU DE OPÇÕES               ");
        System.out.println();
        System.out.println("(1) Cadastro de nova conta");
        System.out.println("(2) Efetuar saque");
        System.out.println("(3) Efetuar depósito");
        System.out.println("(4) Efetuar transferência");
        System.out.println("(5) Consultar saldo");
        System.out.println("(6) Aumentar limite");
        System.out.println("(7) Histórico de transações");
        System.out.println("(8) Finalizar atendimento");
        System.out.println();
        System.out.println("############################################");
        System.out.println();
    }
}
