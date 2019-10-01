package com.anz.finance.transactions.exception;

public class AccountIdFormatException extends RuntimeException {

    private static final long serialVersionUID = 8766131564136982306L;

    public AccountIdFormatException(String message) {
	super(message);
    }

}
