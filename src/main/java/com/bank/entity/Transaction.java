package com.bank.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // DEPOSIT / WITHDRAW

    private double amount;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount account;
}
