package com.sg.account.infrastructure.repository;

import com.sg.account.domain.model.Account;
import com.sg.account.domain.out.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryAdapter implements AccountRepository {
    private Account account = new Account();

    @Override
    public void save(Account account) {
        this.account = account;
    }

    @Override
    public Account load() {
        return account == null ? new Account() : account;
    }
}
