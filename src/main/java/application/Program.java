package application;

import entities.Account;
import entities.BankingExceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============================================");
        System.out.println("             APLICAÇÃO BANCÁRIA             ");
        System.out.println("============================================");
        System.out.println();
        List<Account> accountList = new ArrayList<>();
        int options = 0;
        while (options != 6) {
            screenMenu();
            System.out.print("Digite uma opção para acessar: ");
            options = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (options) {
                case 1:
                    accountList.add(createNewAccount());
                    break;
                case 2:
                    break;
                case 3:
                    depositAccount(accountList);
                    break;
                case 4:
                    break;
                case 5:
                    viewBalance(accountList);
                    break;
                case 6:
                    System.out.println("Muito obrigado por confiar em nosso banco! Volte sempre :-)");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }


        }
    }

    private static Account createNewAccount() {
        System.out.println("--------------------------------------------");
        System.out.println("          Cadastro de novo Cliente:         ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número da conta: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o número da agência: ");
        int agencyNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do cliente: ");
        String clientName = scanner.nextLine();

        System.out.print("Digite o limite da conta: ");
        double accountLimit = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o tipo de conta bancária (CC/CP): ");
        String accountType = scanner.nextLine();
        System.out.println("Conta criada com sucesso!");
        System.out.println();
        return new Account(accountNumber, agencyNumber, clientName, accountLimit, accountType);
    }

    private static void depositAccount(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("                  Depósito:                 ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();
        try {
            if (accountList.isEmpty()) {
                throw new BankingExceptions("Não há contas cadastradas até o momento");
            }
            System.out.print("Digite o número da conta: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o valor a ser depositado: ");
            int depositAmount = scanner.nextInt();
            scanner.nextLine();
            account.deposit(accountNumber, depositAmount, accountList);
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    private static void viewBalance(List<Account> accountList) {
        System.out.println("--------------------------------------------");
        System.out.println("              Verificar saldo:              ");
        System.out.println("--------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();
        try {
            if (accountList.isEmpty()) {
                throw new BankingExceptions("Não há contas cadastradas até o momento");
            }
            System.out.print("Digite o número da conta: ");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.println(account.viewBalance(accountNumber, accountList));
        } catch (BankingExceptions exceptions) {
            System.out.println(exceptions.getMessage());
        }
    }

    private static void screenMenu() {
        System.out.flush();
        System.out.println("############################################");
        System.out.println("               MENU DE OPÇÕES               ");
        System.out.println();
        System.out.println("(1) Cadastro de nova conta");
        System.out.println("(2) Efetuar saque");
        System.out.println("(3) Efetuar depósito");
        System.out.println("(4) Efetuar transferência");
        System.out.println("(5) Consultar saldo");
        System.out.println("(6) Finalizar atendimento");
        System.out.println("############################################");
    }
}
