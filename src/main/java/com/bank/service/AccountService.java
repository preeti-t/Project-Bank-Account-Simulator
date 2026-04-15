package com.bank.service;

import com.bank.dao.AccountDAO;
import com.bank.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private AccountDAO dao = new AccountDAO();

    public void transfer(int fromId, int toId, double amount) {

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false); //  it starts transaction

            double fromBalance = dao.getBalance(fromId, conn);

            if (fromBalance < amount) {
                throw new RuntimeException("Insufficient funds");
            }

            double toBalance = dao.getBalance(toId, conn);

            dao.updateBalance(fromId, fromBalance - amount, conn);
            dao.updateBalance(toId, toBalance + amount, conn);

            conn.commit(); // success

            logger.info("Transfer successful: {} -> {} amount {}", fromId, toId, amount);

        } catch (Exception e) {
            logger.error("Transaction failed: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}