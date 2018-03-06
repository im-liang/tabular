package com.tabular.tabular.exception;

/**
 *
 */
public class NoSuchOwnerException extends RuntimeException {

	public NoSuchOwnerException(String message) {
		super(message);
	}

	public NoSuchOwnerException(String message, Throwable cause) {
		super(message, cause);
	}

}