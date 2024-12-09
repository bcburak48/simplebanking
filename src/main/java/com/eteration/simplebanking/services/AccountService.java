package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account findAccount(String accountNumber) {
        return repository.findById(accountNumber)
                .orElse(null);
    }

    public void save(Account account) {
        repository.save(account);
    }
}
