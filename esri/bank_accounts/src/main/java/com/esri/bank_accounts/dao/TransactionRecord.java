package com.esri.bank_accounts.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@NamedQuery(name="TransactionRecord.findAll", query="SELECT record FROM TransactionRecord record")
@NamedQuery(name="TransactionRecord.findById", query="SELECT record FROM TransactionRecord record WHERE record.transactionId = :transactionId")
@NamedQuery(name="TransactionRecord.findByTransactionDate", query="SELECT record FROM TransactionRecord record WHERE record.transactionDate = :transactionDate")
public class TransactionRecord implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column(name = "ACCOUNT_NO")
    private Long accountNumber;
    @Column(name = "DETAILS")
    private String details;
    @Column(name = "TRANSACTION_DATE")
    private String transactionDate;
    @Column(name = "VALUE_DATE")
    private String valueDate;
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "BALANCE_AMOUNT")
    private Double balanceAmount;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

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

    public TransactionRecord() {
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

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
