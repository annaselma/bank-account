package com.sg.account.domain.in;

import com.sg.account.domain.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountOperations {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
    List<Transaction> getStatement();
    BigDecimal getBalance();
}
