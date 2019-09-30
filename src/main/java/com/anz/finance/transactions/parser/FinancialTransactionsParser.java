package com.anz.finance.transactions.parser;

import static com.anz.finance.transactions.constants.BalanceCalculatorConstants.CSV_FILE_PATH;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.anz.finance.transactions.model.TransactionRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

/**
 * Financial Transactions Parser class to parse csv file, using Opencsv library,
 * containing financial transaction records
 */
public class FinancialTransactionsParser {

    private static FinancialTransactionsParser instance;

    private FinancialTransactionsParser() {
    }

    public static FinancialTransactionsParser getInstance() {
	if (instance == null) {
	    instance = new FinancialTransactionsParser();
	}
	return instance;
    }

    public List<TransactionRecord> parseFinancialTransactions() {

	BufferedReader reader = new BufferedReader(
		new InputStreamReader(FinancialTransactionsParser.class.getResourceAsStream(CSV_FILE_PATH)));
	List<TransactionRecord> transactionRecords = new CsvToBeanBuilder<TransactionRecord>(reader)
		.withIgnoreLeadingWhiteSpace(true).withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
		.withType(TransactionRecord.class).build().parse();
	return transactionRecords;
    }
}
