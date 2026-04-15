package com.bank.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.bank.entity.BankAccount;
import com.bank.config.HibernateUtil;

public class BankAccountDAO {

    public void save(BankAccount account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(account);

        tx.commit();
        session.close();
    }

    public BankAccount get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BankAccount account = session.get(BankAccount.class, id);
        session.close();
        return account;
    }

    public void update(BankAccount account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(account);

        tx.commit();
        session.close();
    }
}