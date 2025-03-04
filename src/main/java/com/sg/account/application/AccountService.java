package com.sg.account.application;

import com.sg.account.domain.in.AccountOperations;
import com.sg.account.domain.model.Account;
import com.sg.account.domain.model.Transaction;
import com.sg.account.domain.out.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService implements AccountOperations {
    private final AccountRepository accountRepository;
    private Account account;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.account = accountRepository.load();
        if (this.account == null) {
            this.account = new Account();
        }
    }
    @Override
    public void deposit(BigDecimal amount) {
        account.deposit(amount);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        account.withdraw(amount);
        accountRepository.save(account);
    }

    @Override
    public List<Transaction> getStatement() {
        return account.getStatement();
    }

    @Override
    public BigDecimal getBalance() {
        return account.getBalance();
    }
}
