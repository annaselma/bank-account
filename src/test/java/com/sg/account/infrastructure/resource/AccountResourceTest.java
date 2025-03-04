package com.sg.account.infrastructure.resource;

import com.sg.account.application.AccountService;
import com.sg.account.domain.model.Account;
import com.sg.account.domain.out.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
class AccountResourceTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @MockitoBean
    private AccountRepository accountRepository;

    private Account testAccount;


    @BeforeEach
    void setUp() {
        testAccount = new Account();
        when(accountRepository.load()).thenReturn(testAccount);
    }

    @Test
    void shouldDepositMoney() throws Exception {
        mockMvc.perform(post("/api/account/deposit").param("amount", "100")).andExpect(status().isOk()).andExpect(content().string("Deposit successful"));
    }

    @Test
    void shouldWithdrawMoney() throws Exception {
        accountService.deposit(BigDecimal.valueOf(200));
        mockMvc.perform(post("/api/account/withdraw").param("amount", "100")).andExpect(status().isOk()).andExpect(content().string("Withdrawal successful"));
    }
//TODO Fix the test

//    @Test
//    void shouldNotWithdrawMoreThanBalance() throws Exception {
//        doThrow(new IllegalArgumentException("Insufficient funds")).when(accountService).withdraw(any(BigDecimal.class));
//
//        mockMvc.perform(post("/api/account/withdraw").param("amount", "100")) // Trying to withdraw more than available balance
//                .andExpect(status().isBadRequest());
//    }

    @Test
    void shouldGetBalance() throws Exception {
        testAccount.deposit(BigDecimal.valueOf(100));
        when(accountRepository.load()).thenReturn(testAccount);

        mockMvc.perform(get("/api/account/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    void shouldGetStatement() throws Exception {
        testAccount.deposit(BigDecimal.valueOf(100));
        testAccount.withdraw(BigDecimal.valueOf(50));
        when(accountRepository.load()).thenReturn(testAccount);

        mockMvc.perform(get("/api/account/statement"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }
}