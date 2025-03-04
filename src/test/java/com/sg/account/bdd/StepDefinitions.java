package com.sg.account.bdd;

import com.sg.account.application.AccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class StepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService accountService;

    private ResultActions resultActions;

    @Given("the account has an initial balance of {int}")
    public void theAccountHasAnInitialBalanceOf(int initialBalance) {
        when(accountService.getBalance()).thenReturn(BigDecimal.valueOf(initialBalance));
    }

    @When("the client deposits {int}")
    public void theClientDeposits(int amount) throws Exception {
        doNothing().when(accountService).deposit(BigDecimal.valueOf(amount));
        resultActions = mockMvc.perform(post("/api/account/deposit")
                .param("amount", String.valueOf(amount)));
    }

    @When("the client withdraws {int}")
    public void theClientWithdraws(int amount) throws Exception {
        doNothing().when(accountService).withdraw(BigDecimal.valueOf(amount));
        resultActions = mockMvc.perform(post("/api/account/withdraw")
                .param("amount", String.valueOf(amount)));
    }

    @When("the client tries to withdraw {int}")
    public void theClientTriesToWithdraw(int amount) throws Exception {
        doThrow(new IllegalArgumentException("Insufficient funds"))
                .when(accountService).withdraw(BigDecimal.valueOf(amount));
        resultActions = mockMvc.perform(post("/api/account/withdraw")
                .param("amount", String.valueOf(amount)));
    }

    @Then("the account balance should be {int}")
    public void theAccountBalanceShouldBe(int expectedBalance) throws Exception {
        when(accountService.getBalance()).thenReturn(BigDecimal.valueOf(expectedBalance));
        resultActions = mockMvc.perform(get("/api/account/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedBalance)));
    }

    @Then("the system should return an error message {string}")
    public void theSystemShouldReturnAnErrorMessage(String expectedMessage) throws Exception {
        resultActions.andExpect(status().isBadRequest())
                .andExpect(content().string(expectedMessage));
    }

    @When("the client checks the balance")
    public void theClientChecksTheBalance() throws Exception {
        resultActions = mockMvc.perform(get("/api/account/balance"));
    }

    @When("the client requests the account statement")
    public void theClientRequestsTheAccountStatement() throws Exception {
        resultActions = mockMvc.perform(get("/api/account/statement"));
    }

    @Then("the system should return a list of transactions")
    public void theSystemShouldReturnAListOfTransactions() throws Exception {
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}