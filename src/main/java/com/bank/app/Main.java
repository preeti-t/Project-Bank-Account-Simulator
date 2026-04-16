package com.bank.app;

import com.bank.entity.BankAccount;
import com.bank.service.AccountService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AccountService service = new AccountService();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. View Accounts");
            System.out.println("2. Add Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    service.showAll();
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Balance: ");
                    BigDecimal bal = sc.nextBigDecimal();

                    BankAccount acc = new BankAccount();
                    acc.setName(name);
                    acc.setBalance(bal);

                    service.createAccount(acc);
                    break;

                case 3:
                    System.out.print("ID: ");
                    Long depId = sc.nextLong();

                    System.out.print("Amount: ");
                    BigDecimal depAmt = sc.nextBigDecimal();

                    service.deposit(depId, depAmt);
                    break;

                case 4:
                    System.out.print("ID: ");
                    Long widId = sc.nextLong();

                    System.out.print("Amount: ");
                    BigDecimal widAmt = sc.nextBigDecimal();

                    service.withdraw(widId, widAmt);
                    break;

                case 5:
                    System.out.println("Bye!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}