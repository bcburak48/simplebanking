package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public void applyOn(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
    }
}