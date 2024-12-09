package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void applyOn(Account account) {
        account.deposit(this.getAmount());
    }
}
