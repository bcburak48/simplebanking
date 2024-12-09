package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    private String accountNumber;
    private String owner;
    private double balance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "accountNumber")
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (this.balance < amount) {
            throw new InsufficientBalanceException("Insufficient balance to withdraw " + amount);
        }
        this.balance -= amount;
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.applyOn(this);
        this.transactions.add(transaction);
    }
}
