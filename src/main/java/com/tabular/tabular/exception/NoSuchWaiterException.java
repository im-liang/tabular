package com.tabular.tabular.exception;

/**
 *
 */
public class NoSuchWaiterException extends RuntimeException {

	public NoSuchWaiterException(String message) {
		super(message);
	}

	public NoSuchWaiterException(String message, Throwable cause) {
		super(message, cause);
	}

}