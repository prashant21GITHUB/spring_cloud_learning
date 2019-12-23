package com.esri.bank_accounts.controllers;

import com.esri.bank_accounts.dao.TransactionRecord;
import com.esri.bank_accounts.dao.TransactionRepository;
import com.esri.bank_accounts.utils.CsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "transactions")
public class TransactionsController {

    @Autowired
    private TransactionRepository repository;

    @PostMapping("/initDB")
    public String init() {
        List<TransactionRecord> transactionRecords = CsvFileReader.readTransactionRecords("Bank_transactions48435d6.csv");
        repository.saveAll(transactionRecords);
        return "Success";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> createNewTransaction(@RequestBody TransactionRecord transactionRecord) {
        try {
            repository.save(transactionRecord);
            return new ResponseEntity<>(new ServiceResponse(transactionRecord, ""), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ServiceResponse<>(transactionRecord, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{transactionId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> updateTransaction(@PathVariable Long transactionId, @RequestBody TransactionRecord transactionRecord) {
        try {
            repository.update(transactionId, transactionRecord);
            return new ResponseEntity<>(new ServiceResponse(transactionRecord, ""), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ServiceResponse<>(transactionRecord, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> deleteTransaction(@PathVariable Long transactionId) {
        try {
            repository.deleteById(transactionId);
            return new ResponseEntity<>(new ServiceResponse(transactionId, "Record deleted successfully"), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ServiceResponse<>(transactionId, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<List<TransactionRecord>>>
                                getAllTransactionsWithPagination(@RequestParam Integer start, @RequestParam Integer size) {
        try {
            Integer pageNumber = start;
            Integer pageSize = size;
            List<TransactionRecord> transactionRecords = repository.findAllByPages(pageNumber, pageSize);
            return new ResponseEntity<>(new ServiceResponse<>(transactionRecords, ""),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ServiceResponse<>(Collections.EMPTY_LIST, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<List<TransactionRecord>>> getAllTransactions() {
        try {
            List<TransactionRecord> transactionRecords = repository.findAll();
            return new ResponseEntity<>(new ServiceResponse<>(transactionRecords, ""),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ServiceResponse<>(Collections.EMPTY_LIST, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/date/{transactionDate}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse<List<TransactionRecord>>> getAllTransactionsByDate(@PathVariable String transactionDate) {
        try {
            transactionDate = transactionDate.replace("_", " ");
            if(transactionDate.startsWith("0")) transactionDate = transactionDate.substring(1);
            List<TransactionRecord> transactionRecords = repository.findByDate(transactionDate);
            return new ResponseEntity<>(new ServiceResponse<>(transactionRecords, ""),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ServiceResponse<>(Collections.EMPTY_LIST, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
