package com.anz.finance.transactions.calculator;

import static com.anz.finance.transactions.constants.BalanceCalculatorConstants.DATE_TIME_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.anz.finance.transactions.model.BalanceCalculatorResponse;
import com.anz.finance.transactions.model.TransactionRecord;
import com.anz.finance.transactions.model.TransactionType;

/**
 * BalanceCalculator - calculates the relative balance for an account in a given
 * time frame
 */
public class BalanceCalculator {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public static BalanceCalculatorResponse calculateBalance(List<TransactionRecord> financialTransactions,
	    String accountId, String fromDateTimeString, String toDateTimeString) {

	List<TransactionRecord> paymentTransactionRecords = getPaymentTransactions(financialTransactions, accountId,
		fromDateTimeString, toDateTimeString);

	List<TransactionRecord> reversalTransactionRecords = financialTransactions.stream()
		.filter(interimTransaction -> interimTransaction.getTransactionType()
			.equals(TransactionType.REVERSAL.getTransactionType())
			&& (interimTransaction.getFromAccountId().equals(accountId)
				|| interimTransaction.getToAccountId().equals(accountId)))
		.collect(Collectors.toList());

	if (reversalTransactionRecords != null && !reversalTransactionRecords.isEmpty()) {
	    checkReversalAndGetFilteredPaymentTransactions(paymentTransactionRecords, reversalTransactionRecords);
	}

	double sum = calculateSum(accountId, paymentTransactionRecords);

	return new BalanceCalculatorResponse(sum, paymentTransactionRecords.size());
    }

    private static List<TransactionRecord> getPaymentTransactions(List<TransactionRecord> financialTransactions,
	    String accountId, String fromDateTimeString, String toDateTimeString) {
	LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeString, formatter);
	LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeString, formatter);

	List<TransactionRecord> filteredPaymentTransactionRecords = financialTransactions.stream()
		.filter(interimTransaction -> interimTransaction.getTransactionType()
			.equals(TransactionType.PAYMENT.getTransactionType())
			&& (LocalDateTime.parse(interimTransaction.getCreateAt(), formatter).isAfter(fromDateTime)
				|| LocalDateTime.parse(interimTransaction.getCreateAt(), formatter)
					.isEqual(fromDateTime))
			&& (LocalDateTime.parse(interimTransaction.getCreateAt(), formatter).isBefore(toDateTime)
				|| LocalDateTime.parse(interimTransaction.getCreateAt(), formatter).isEqual(toDateTime))
			&& (interimTransaction.getFromAccountId().equals(accountId)
				|| interimTransaction.getToAccountId().equals(accountId)))
		.collect(Collectors.toList());

	return filteredPaymentTransactionRecords;
    }

    private static List<TransactionRecord> checkReversalAndGetFilteredPaymentTransactions(
	    List<TransactionRecord> filteredTransactionRecords, List<TransactionRecord> reversalTransactionRecords) {
	Iterator<TransactionRecord> iterator = filteredTransactionRecords.iterator();
	while (iterator.hasNext()) {
	    boolean match = reversalTransactionRecords.stream().anyMatch(interimTransaction -> interimTransaction
		    .getRelatedTransaction().equals(iterator.next().getTransactionId()));
	    if (match) {
		iterator.remove();
	    }
	}
	return filteredTransactionRecords;
    }

    private static double calculateSum(String accountId, List<TransactionRecord> filteredTransactionRecords) {
	double sum = 0.0;
	double transactionAmount = 0.0;

	for (TransactionRecord transactionRecord : filteredTransactionRecords) {
	    String fromAccountId = transactionRecord.getFromAccountId();
	    String toAccountId = transactionRecord.getToAccountId();
	    transactionAmount = transactionRecord.getAmount();
	    if (accountId.equals(fromAccountId)) {
		sum -= transactionAmount;
	    } else if (accountId.equals(toAccountId)) {
		sum += transactionAmount;
	    }
	}
	return sum;
    }
}
