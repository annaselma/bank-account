package com.sg.account.bdd;

import com.sg.account.domain.model.Account;
import com.sg.account.domain.out.AccountRepository;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.sg.account.bdd")
@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
class AccountResourceBDDTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountRepository accountService;

    @MockitoBean
    private AccountRepository accountRepository;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
        when(accountRepository.load()).thenReturn(testAccount);
    }
}
