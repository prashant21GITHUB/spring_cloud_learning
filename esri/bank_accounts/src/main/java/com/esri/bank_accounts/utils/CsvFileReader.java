package com.esri.bank_accounts.utils;

import com.esri.bank_accounts.dao.TransactionRecord;
import com.esri.bank_accounts.dao.TransactionType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {

    public static List<TransactionRecord> readTransactionRecords(String csvFilePath) {
        List<TransactionRecord> transactionRecords = new ArrayList<>();
        File file = new File(csvFilePath);

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //Skipping the first line as It is Header
            reader.readLine();
            //Account No,Date,Transaction Details,Value Date,Withdrawal AMT,Deposit AMT,Balance AMT
            String transaction;
            while((transaction = reader.readLine()) != null) {
                String detailsArr[] = transaction.split(",");
                String accountNum = detailsArr[0];
                String transactionDate = detailsArr[1];
                String transactionDetails = detailsArr[2];
                String valueDate = detailsArr[3];
                String withdrawlAmount = detailsArr[4];
                String depositAmount = detailsArr[5];
                String balanceAmount = detailsArr[6];
                String amount = withdrawlAmount;
                TransactionType transactionType = TransactionType.Withdrawl;
                if(amount == null || amount.isEmpty()) {
                    amount = depositAmount;
                    transactionType = TransactionType.Deposit;
                }
                TransactionRecord record = new TransactionRecord(Long.valueOf(accountNum), transactionDetails, transactionDate,
                        valueDate, Double.valueOf(amount), Double.valueOf(balanceAmount), transactionType);
                transactionRecords.add(record);
            }

        } catch (Exception ex) {
        }
        return transactionRecords;
    }
}
