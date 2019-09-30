package com.anz.finance.transactions.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.anz.finance.transactions.calculator.BalanceCalculator;
import com.anz.finance.transactions.model.BalanceCalculatorResponse;
import com.anz.finance.transactions.model.TransactionRecord;

public class BalanceCalculatorTest {

    private static List<TransactionRecord> allTransactions;
    private static List<TransactionRecord> paymentOnlyTransactions;

    @BeforeAll
    public static void loadAllTransactions() {
	allTransactions = TestDataLoader.getAllTransationsRecords();
	paymentOnlyTransactions = TestDataLoader.getPaymentOnlyTransactionsNoReversal();
    }

    @Test
    void testBalanceCalculatorWithFromAccountIdAsInput() {

	String accountId = "ACC334455";
	String fromDateTimeString = "20/10/2018 12:00:00";
	String toDateTimeString = "20/10/2018 19:00:00";

	BalanceCalculatorResponse actualResponse = BalanceCalculator.calculateBalance(allTransactions, accountId,
		fromDateTimeString, toDateTimeString);

	BalanceCalculatorResponse expectedResponse = new BalanceCalculatorResponse(-25.0, 1);

	assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testBalanceCalculatorWithToAccountAsInput() {

	String accountId = "ACC778899";
	String fromDateTimeString = "21/10/2018 08:30:00";
	String toDateTimeString = "21/10/2018 10:30:00";

	BalanceCalculatorResponse actualResponse = BalanceCalculator.calculateBalance(allTransactions, accountId,
		fromDateTimeString, toDateTimeString);

	BalanceCalculatorResponse expectedResponse = new BalanceCalculatorResponse(7.25, 1);

	assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testBalanceCalculatorWithToAccountAndNoReversalTransaction() {

	String accountId = "ACC998877";
	String fromDateTimeString = "20/10/2018 17:00:00";
	String toDateTimeString = "20/10/2018 18:00:00";

	BalanceCalculatorResponse actualResponse = BalanceCalculator.calculateBalance(paymentOnlyTransactions,
		accountId, fromDateTimeString, toDateTimeString);

	BalanceCalculatorResponse expectedResponse = new BalanceCalculatorResponse(5.5, 2);

	assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testBalanceCalculatorToAccountEqualTime() {

	String accountId = "ACC998877";
	String fromDateTimeString = "20/10/2018 18:00:00";
	String toDateTimeString = "20/10/2018 19:45:00";

	BalanceCalculatorResponse actualResponse = BalanceCalculator.calculateBalance(paymentOnlyTransactions,
		accountId, fromDateTimeString, toDateTimeString);

	BalanceCalculatorResponse expectedResponse = new BalanceCalculatorResponse(-5.0, 1);

	assertEquals(expectedResponse, actualResponse);
    }
}
