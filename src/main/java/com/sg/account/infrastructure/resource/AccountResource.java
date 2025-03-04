package com.sg.account.infrastructure.resource;


import com.sg.account.application.AccountService;
import com.sg.account.domain.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*")
public class AccountResource {
    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam BigDecimal amount) {
        accountService.deposit(amount);
        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam BigDecimal amount) {
        accountService.withdraw(amount);
        return ResponseEntity.ok("Withdrawal successful");
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        return ResponseEntity.ok(accountService.getBalance());
    }

    @GetMapping("/statement")
    public ResponseEntity<List<Transaction>> getStatement() {
        return ResponseEntity.ok(accountService.getStatement());
    }
}
