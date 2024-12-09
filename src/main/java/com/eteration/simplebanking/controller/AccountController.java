package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account/v1")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = service.findAccount(accountNumber);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(
            @PathVariable String accountNumber,
            @RequestBody DepositTransaction transaction) throws InsufficientBalanceException {
        Account account = service.findAccount(accountNumber);
        account.post(transaction);
        service.save(account);
        return ResponseEntity.ok(new TransactionStatus("OK", transaction.getApprovalCode()));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(
            @PathVariable String accountNumber,
            @RequestBody WithdrawalTransaction transaction) throws InsufficientBalanceException {
        Account account = service.findAccount(accountNumber);
        account.post(transaction);
        service.save(account);
        return ResponseEntity.ok(new TransactionStatus("OK", transaction.getApprovalCode()));
    }
}
