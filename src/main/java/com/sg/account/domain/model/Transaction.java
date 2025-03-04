package com.sg.account.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final LocalDateTime date;
    private final BigDecimal amount;
    private final BigDecimal balanceAfterTransaction;

    public Transaction(LocalDateTime date, BigDecimal amount, BigDecimal balanceAfterTransaction) {
        this.date = date;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }
}

