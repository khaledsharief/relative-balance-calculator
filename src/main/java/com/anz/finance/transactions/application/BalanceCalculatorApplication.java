package com.anz.finance.transactions.application;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.anz.finance.transactions.calculator.BalanceCalculator;
import com.anz.finance.transactions.model.BalanceCalculatorResponse;
import com.anz.finance.transactions.model.TransactionRecord;
import com.anz.finance.transactions.parser.FinancialTransactionsParser;

/**
 * Balance Calculator application
 *
 */
public class BalanceCalculatorApplication {

    public static void main(String[] args) {

	List<TransactionRecord> financialTransactions = FinancialTransactionsParser.getInstance()
		.parseFinancialTransactions();

	if (financialTransactions != null && !financialTransactions.isEmpty()) {
	    Scanner scanner = null;
	    try {
		scanner = new Scanner(System.in);
		scanner.useDelimiter(Pattern.compile("([\n;]|(\r\n))+"));
		System.out.println("Enter number account ID : ");
		String accountId = scanner.nextLine();
		System.out.println("Enter the from date in dd/MM/yyyy HH:mm:ss format: ");
		String fromDateTimeString = scanner.nextLine();
		System.out.println("Enter the to date in dd/MM/yyyy HH:mm:ss format: ");
		String toDateTimeString = scanner.nextLine();

		BalanceCalculatorResponse response = BalanceCalculator.calculateBalance(financialTransactions,
			accountId, fromDateTimeString, toDateTimeString);

		System.out.println("Relative balance for the period is: " + response.getRelativeBalance());
		System.out.println("Number of transactions included is: " + response.getNoOfTransactions());

	    } catch (Exception exception) {
		System.err.println("Exception occurred running Balance Calculator application " + exception);
	    } finally {
		if (scanner != null) {
		    scanner.close();
		}
	    }
	}
    }
}
