package com.esri.bank_accounts.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TRANSACTION_DETAILS")
public class TransactionRecord implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column(name = "ACCOUNT_NO")
    final private Long accountNumber;
    @Column(name = "DETAILS")
    final private String details;
    @Column(name = "TRANSACTION_DATE")
    final private String transactionDate;
    @Column(name = "VALUE_DATE")
    final private String valueDate;
    @Column(name = "AMOUNT")
    final private Double amount;
    @Column(name = "BALANCE_AMOUNT")
    final private Double balanceAmount;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    final private TransactionType transactionType;

    public TransactionRecord(Long accountNumber, String details, String transactionDate,
                             String valueDate, Double amount, Double balanceAmount, TransactionType transactionType) {
        this.accountNumber = accountNumber;
        this.details = details;
        this.transactionDate = transactionDate;
        this.valueDate = valueDate;
        this.amount = amount;
        this.balanceAmount = balanceAmount;
        this.transactionType = transactionType;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getDetails() {
        return details;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getValueDate() {
        return valueDate;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
