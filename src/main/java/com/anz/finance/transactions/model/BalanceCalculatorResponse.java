package com.anz.finance.transactions.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class BalanceCalculatorResponse {

    private double relativeBalance;

    private int noOfTransactions;

}
