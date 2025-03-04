package com.sg.account.application;

import com.sg.account.domain.model.Account;
import com.sg.account.domain.out.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {
    private AccountRepository accountRepository;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        when(accountRepository.load()).thenReturn(new Account());
        accountService = new AccountService(accountRepository);
    }

    @Test
    void shouldDepositMoney() {
        accountService.deposit(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(100), accountService.getBalance());
        Mockito.verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void shouldWithdrawMoney() {
        accountService.deposit(BigDecimal.valueOf(200));
        accountService.withdraw(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(100), accountService.getBalance());
        Mockito.verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    void shouldNotWithdrawMoreThanBalance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                accountService.withdraw(BigDecimal.valueOf(50)));
        assertEquals("Insufficient funds", exception.getMessage());
    }
}
