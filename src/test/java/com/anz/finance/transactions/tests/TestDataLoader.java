package com.anz.finance.transactions.tests;

import java.util.ArrayList;
import java.util.List;

import com.anz.finance.transactions.model.TransactionRecord;
import com.anz.finance.transactions.model.TransactionType;

public class TestDataLoader {
    
    public static List<TransactionRecord> getAllTransationsRecords() {
	List<TransactionRecord> transactionRecords = getPaymentOnlyTransactionsNoReversal();

	TransactionRecord transactionRecord4 = new TransactionRecord();
	transactionRecord4.setTransactionId("TX10004");
	transactionRecord4.setFromAccountId("ACC334455");
	transactionRecord4.setToAccountId("ACC998877");
	transactionRecord4.setCreateAt("20/10/2018 19:45:00");
	transactionRecord4.setAmount(10.50);
	transactionRecord4.setTransactionType(TransactionType.REVERSAL.getTransactionType());
	transactionRecord4.setRelatedTransaction("TX10002");

	transactionRecords.add(transactionRecord4);

	TransactionRecord transactionRecord5 = new TransactionRecord();
	transactionRecord5.setTransactionId("TX10005");
	transactionRecord5.setFromAccountId("ACC334455");
	transactionRecord5.setToAccountId("ACC778899");
	transactionRecord5.setCreateAt("21/10/2018 09:30:00");
	transactionRecord5.setAmount(7.25);
	transactionRecord5.setTransactionType(TransactionType.PAYMENT.getTransactionType());

	transactionRecords.add(transactionRecord5);

	return transactionRecords;
    }

    public static List<TransactionRecord> getPaymentOnlyTransactionsNoReversal() {

	List<TransactionRecord> transactionRecords = new ArrayList<TransactionRecord>();

	TransactionRecord transactionRecord1 = new TransactionRecord();
	transactionRecord1.setTransactionId("TX10001");
	transactionRecord1.setFromAccountId("ACC334455");
	transactionRecord1.setToAccountId("ACC778899");
	transactionRecord1.setCreateAt("20/10/2018 12:47:55");
	transactionRecord1.setAmount(25.00);
	transactionRecord1.setTransactionType(TransactionType.PAYMENT.getTransactionType());

	transactionRecords.add(transactionRecord1);

	TransactionRecord transactionRecord2 = new TransactionRecord();
	transactionRecord2.setTransactionId("TX10002");
	transactionRecord2.setFromAccountId("ACC334455");
	transactionRecord2.setToAccountId("ACC998877");
	transactionRecord2.setCreateAt("20/10/2018 17:33:43");
	transactionRecord2.setAmount(10.50);
	transactionRecord2.setTransactionType(TransactionType.PAYMENT.getTransactionType());

	transactionRecords.add(transactionRecord2);

	TransactionRecord transactionRecord3 = new TransactionRecord();
	transactionRecord3.setTransactionId("TX10003");
	transactionRecord3.setFromAccountId("ACC998877");
	transactionRecord3.setToAccountId("ACC778899");
	transactionRecord3.setCreateAt("20/10/2018 18:00:00");
	transactionRecord3.setAmount(5.00);
	transactionRecord3.setTransactionType(TransactionType.PAYMENT.getTransactionType());

	transactionRecords.add(transactionRecord3);

	return transactionRecords;
    }

}
