package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private LocalDateTime date = LocalDateTime.now();
    private String approvalCode;

    public Transaction(double amount) {
        this.amount = amount;
        this.approvalCode = java.util.UUID.randomUUID().toString();
    }

    public abstract void applyOn(Account account) throws InsufficientBalanceException;
}
