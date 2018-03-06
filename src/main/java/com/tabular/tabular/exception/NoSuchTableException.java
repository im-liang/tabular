package com.tabular.tabular.exception;

/**
 *
 */
public class NoSuchTableException extends RuntimeException {

	public NoSuchTableException(String message) {
		super(message);
	}

	public NoSuchTableException(String message, Throwable cause) {
		super(message, cause);
	}

}