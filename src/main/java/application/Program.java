package application;

import entities.Account;
import entities.BankingExceptions;

import java.util.ArrayList;
import java.util.List;
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
        int options = -1;
        while (options != 7) {
            screenMenu();
            System.out.print("Digite uma opção para acessar: ");
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
                    System.out.println("Muito obrigado por confiar em nosso banco! Volte sempre :-)");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }
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
        try (Scanner scanner = new Scanner(System.in)) {
            if (accountList.isEmpty()) {
                throw new BankingExceptions("Não há contas cadastradas até o momento");
            }
            System.out.print("Digite o número da conta: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o valor a ser depositado: ");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine();
            Account account = findAccount(accountNumber, accountList);
            if (account != null) {
                account.depositAccount(accountNumber, depositAmount, accountList);
            } else {
                throw new BankingExceptions("Não foi possível encontrar a conta.");
            }
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    private static void withdrawAccount(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("                   Saque:                   ");
        System.out.println("--------------------------------------------");
        try (Scanner scanner = new Scanner(System.in)) {
            if (accountList.isEmpty()) {
                throw new BankingExceptions("Não há contas cadastradas até o momento");
            }
            System.out.print("Digite o número da conta: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o valor a ser sacado: ");
            double withdrawAmount = scanner.nextDouble();
            scanner.nextLine();
            Account account = findAccount(accountNumber, accountList);
            if (account != null) {
                account.withdrawAccount(accountNumber, withdrawAmount, accountList);
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
        try (Scanner scanner = new Scanner(System.in)) {
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
            Account account = findAccount(accountNumberSender, accountList);
            if (account != null) {
                account.transferBetweenAccounts(accountNumberSender, accountNumberReceiver, transferAmount, accountList);
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
        try (Scanner scanner = new Scanner(System.in)) {
            if (accountList.isEmpty()) {
                throw new BankingExceptions("Não há contas cadastradas até o momento");
            }
            System.out.print("Digite o número da conta: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o novo limite: ");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine();
            Account account = findAccount(accountNumber, accountList);
            if (account != null) {
                account.changeAccountLimit(accountNumber, depositAmount, accountList);
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
        try (Scanner scanner = new Scanner(System.in)) {
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
        System.out.println("(7) Finalizar atendimento");
        System.out.println();
        System.out.println("############################################");
        System.out.println();
    }
}
