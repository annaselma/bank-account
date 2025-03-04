package com.sg.account.domain.out;

import com.sg.account.domain.model.Account;

public interface AccountRepository {
    void save(Account account);
    Account load();
}
