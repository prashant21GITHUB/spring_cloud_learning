package com.esri.bank_accounts.controllers;

import com.esri.bank_accounts.dao.TransactionRecord;
import com.esri.bank_accounts.dao.TransactionRepository;
import com.esri.bank_accounts.utils.CsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionRepository repository;

    @PostMapping("/initDB")
    public String init() {
        List<TransactionRecord> transactionRecords = CsvFileReader.readTransactionRecords("Bank_transactions48435d6.csv");
        repository.saveAll(transactionRecords);
        return "Success";
    }

    @GetMapping("/")
    public String check() {
//        List<TransactionRecord> transactionRecords = CsvFileReader.readTransactionRecords("Bank_transactions48435d6.csv");
//        repository.saveAll(transactionRecords);
        return "Success";
    }
}
