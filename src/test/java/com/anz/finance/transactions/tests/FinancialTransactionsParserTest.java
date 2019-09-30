package com.anz.finance.transactions.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.anz.finance.transactions.model.TransactionRecord;
import com.anz.finance.transactions.parser.FinancialTransactionsParser;

public class FinancialTransactionsParserTest {

    private static List<TransactionRecord> expectedTransactionRecords;

    @BeforeAll
    public static void loadAllTransactions() {
	expectedTransactionRecords = TestDataLoader.getAllTransationsRecords();
    }

    @Test
    void testParseFinancialTransactions() {
	List<TransactionRecord> actualTransactionRecords = FinancialTransactionsParser.getInstance()
		.parseFinancialTransactions();
	assertEquals(expectedTransactionRecords, actualTransactionRecords);
    }
}
