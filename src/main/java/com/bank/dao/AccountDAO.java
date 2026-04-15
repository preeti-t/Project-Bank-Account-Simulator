package com.bank.dao;

import java.sql.*;

public class AccountDAO {

    public double getBalance(int id, Connection conn) throws Exception {
        String sql = "SELECT balance FROM accounts WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble("balance");
        }
        throw new RuntimeException("Account not found");
    }

    public void updateBalance(int id, double amount, Connection conn) throws Exception {
        String sql = "UPDATE accounts SET balance=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, amount);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
}