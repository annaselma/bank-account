package com.sg.account.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void shouldCreateTransactionWithCorrectValues() {
        LocalDateTime now = LocalDateTime.now();
        BigDecimal amount = BigDecimal.valueOf(100);
        BigDecimal balanceAfterTransaction = BigDecimal.valueOf(500);

        Transaction transaction = new Transaction(now, amount, balanceAfterTransaction);

        assertNotNull(transaction);
        assertEquals(now, transaction.getDate());
        assertEquals(amount, transaction.getAmount());
        assertEquals(balanceAfterTransaction, transaction.getBalanceAfterTransaction());
    }
}