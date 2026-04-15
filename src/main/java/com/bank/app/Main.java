package com.bank.app;

import com.bank.entity.BankAccount;
import com.bank.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. View all accounts");
            System.out.println("2. Add account");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewAccounts(factory);
                    break;

                case 2:
                    addAccount(factory, sc);
                    break;

                case 3:
                    factory.close();
                    System.out.println("Bye!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // ---------------- VIEW ACCOUNTS ----------------
    public static void viewAccounts(SessionFactory factory) {
        Session session = factory.openSession();

        List<BankAccount> accounts =
                session.createQuery("from BankAccount", BankAccount.class).list();

        System.out.println("\n--- ACCOUNTS ---");
        for (BankAccount a : accounts) {
            System.out.println(
                    a.getId() + " | " +
                            a.getName() + " | " +
                            a.getBalance()
            );
        }

        session.close();
    }

    // ---------------- ADD ACCOUNT ----------------
    public static void addAccount(SessionFactory factory, Scanner sc) {

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        BigDecimal balance = sc.nextBigDecimal();

        BankAccount account = new BankAccount();
        account.setName(name);
        account.setBalance(balance);

        Session session = factory.openSession();
        session.beginTransaction();

        session.persist(account);

        session.getTransaction().commit();
        session.close();

        System.out.println("Account created!");
    }
}