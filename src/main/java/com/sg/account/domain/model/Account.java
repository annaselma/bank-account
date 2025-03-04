package com.sg.account.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        transactions.add(new Transaction(LocalDateTime.now(), amount, getBalance().add(amount)));
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount.compareTo(getBalance()) > 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        transactions.add(new Transaction(LocalDateTime.now(), amount.negate(), getBalance().subtract(amount)));
    }

    public BigDecimal getBalance() {
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getStatement() {
        return Collections.unmodifiableList(transactions);
    }
}