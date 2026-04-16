package com.bank.service;

import com.bank.dao.BankAccountDAO;
import com.bank.entity.BankAccount;

import java.math.BigDecimal;

public class AccountService {

    private BankAccountDAO dao = new BankAccountDAO();

    // CREATE ACCOUNT
    public void createAccount(BankAccount acc) {
        dao.save(acc);
    }

    // VIEW ALL
    public void showAll() {
        for (BankAccount a : dao.getAll()) {
            System.out.println(a.getId() + " | " + a.getName() + " | " + a.getBalance());
        }
    }

    // DEPOSIT
    public void deposit(Long id, BigDecimal amount) {

        BankAccount acc = dao.getById(id);

        acc.setBalance(acc.getBalance().add(amount));

        dao.save(acc); // update via persist/merge logic
    }

    // WITHDRAW
    public void withdraw(Long id, BigDecimal amount) {

        BankAccount acc = dao.getById(id);

        if (acc.getBalance().compareTo(amount) >= 0) {
            acc.setBalance(acc.getBalance().subtract(amount));
            dao.save(acc);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}