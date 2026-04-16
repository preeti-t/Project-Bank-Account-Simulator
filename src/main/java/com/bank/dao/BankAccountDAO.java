package com.bank.dao;

import com.bank.entity.BankAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.bank.config.HibernateUtil;

import java.util.List;

public class BankAccountDAO {

    SessionFactory factory = HibernateUtil.getSessionFactory();

    public void save(BankAccount account) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.merge(account);

        session.getTransaction().commit();
        session.close();
    }

    public List<BankAccount> getAll() {
        Session session = factory.openSession();

        List<BankAccount> list =
                session.createQuery("from BankAccount", BankAccount.class).list();

        session.close();
        return list;
    }

    public BankAccount getById(Long id) {
        Session session = factory.openSession();

        BankAccount acc = session.get(BankAccount.class, id);

        session.close();
        return acc;
    }
}