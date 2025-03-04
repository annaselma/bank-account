package com.sg.account.infrastructure.repository;

import com.sg.account.domain.model.Account;
import com.sg.account.domain.out.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AccountRepositoryAdapterTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        accountRepository.save(new Account()); // Reset repository state before each test
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Order(1)
    @Test
    @Rollback
    void shouldReturnEmptyAccountIfNotSaved() {
        Account loadedAccount = accountRepository.load();
        assertNotNull(loadedAccount);
        assertEquals(BigDecimal.ZERO, loadedAccount.getBalance());
    }

    @Test
    @Order(2)
    @Rollback
    void shouldSaveAccount() {
        account.deposit(BigDecimal.valueOf(500));
        accountRepository.save(account);
        Account savedAccount = accountRepository.load();
        assertNotNull(savedAccount);
        assertEquals(BigDecimal.valueOf(500), savedAccount.getBalance());
    }

    @Test
    @Order(3)
    @Rollback
    void shouldLoadSavedAccount() {
        account.deposit(BigDecimal.valueOf(300));
        accountRepository.save(account);

        Account loadedAccount = accountRepository.load();
        assertNotNull(loadedAccount);
        assertEquals(BigDecimal.valueOf(300), loadedAccount.getBalance());
    }

   /* @Test
    @Order(4)
    void shouldReturnStatementAsJsonArray() throws Exception {
        mockMvc.perform(get("/api/account/statement"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$").isArray())
                .andExpect((ResultMatcher) jsonPath("$.length()").value(0));
    }
    */
}
