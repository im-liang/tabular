package com.tabular.tabular.exception;

/**
 *
 */
public class NoSuchRelationException extends RuntimeException {

	public NoSuchRelationException(String message) {
		super(message);
	}

	public NoSuchRelationException(String message, Throwable cause) {
		super(message, cause);
	}

}