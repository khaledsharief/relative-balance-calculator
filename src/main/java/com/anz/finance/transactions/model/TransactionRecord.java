package com.anz.finance.transactions.model;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class TransactionRecord {
    @CsvBindByName(column = "transactionId")
    private String transactionId;
    @CsvBindByName(column = "fromAccountId")
    private String fromAccountId;
    @CsvBindByName(column = "toAccountId")
    private String toAccountId;
    @CsvBindByName(column = "createdAt")
    private String createAt;
    @CsvBindByName(column = "amount")
    private Double amount;
    @CsvBindByName(column = "transactionType")
    private String transactionType;
    @CsvBindByName(column = "relatedTransaction")
    private String relatedTransaction;
}
