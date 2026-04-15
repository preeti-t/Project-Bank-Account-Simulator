package com.bank.app;

import com.bank.service.AccountService;

public class Main {
    public static void main(String[] args) {

        AccountService service = new AccountService();

        service.transfer(1, 2, 50);
    }
}