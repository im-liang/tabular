package com.tabular.tabular.exception;

/**
 *
 */
public class NoSuchCustomerException extends RuntimeException {

	public NoSuchCustomerException(String message) {
		super(message);
	}

	public NoSuchCustomerException(String message, Throwable cause) {
		super(message, cause);
	}

}