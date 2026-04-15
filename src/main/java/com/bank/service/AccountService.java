package com.bank.service;

import com.bank.entity.BankAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.bank.config.HibernateUtil;

import java.math.BigDecimal;

public class AccountService {

    SessionFactory factory = HibernateUtil.getSessionFactory();

    public void deposit(Long id, BigDecimal amount) {

        Session session = factory.openSession();
        session.beginTransaction();

        BankAccount acc = session.get(BankAccount.class, id);
        acc.setBalance(acc.getBalance().add(amount));

        session.getTransaction().commit();
        session.close();
    }

    public void withdraw(Long id, BigDecimal amount) {

        Session session = factory.openSession();
        session.beginTransaction();

        BankAccount acc = session.get(BankAccount.class, id);
        acc.setBalance(acc.getBalance().subtract(amount));

        session.getTransaction().commit();
        session.close();
    }
}