package application;

import entities.Account;

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
        screenMenu();
        System.out.print("Digite uma opção para acessar: ");
        int options = scanner.nextInt();
        System.out.println();
        List<Account> accountList = new ArrayList<>();
        while (options !=0) {
            if (options == 1) {
                System.out.println("--------------------------------------------");
                System.out.println("          Cadastro de novo Cliente:         ");
                System.out.println("--------------------------------------------");
                accountList.add(createNewAccount());
                for (Account a : accountList) {
                    System.out.println(a);
                }
            }
            options = 10;
            screenMenu();

        }
    }

    private static Account createNewAccount() {
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
        return new Account(accountNumber, agencyNumber, clientName, accountLimit, accountType);
    }

    private static void screenMenu() {
        System.out.println("############################################");
        System.out.println("               MENU DE OPÇÕES               ");
        System.out.println();
        System.out.println("(1) Cadastro de usuario");
        System.out.println("(2) Efetuar Saque");
        System.out.println("(3) Efetuar depósito");
        System.out.println("(4) Efetuar tranferência");
        System.out.println("############################################");
    }
}
