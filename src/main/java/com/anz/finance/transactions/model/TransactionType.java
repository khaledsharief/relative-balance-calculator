package com.anz.finance.transactions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    PAYMENT("PAYMENT"), 
    REVERSAL("REVERSAL");

    private String transactionType;
}