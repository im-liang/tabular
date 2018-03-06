package com.tabular.tabular.exception;

/**
 *
 */
public class NoSuchRestaurantException extends RuntimeException {

	public NoSuchRestaurantException(String message) {
		super(message);
	}

	public NoSuchRestaurantException(String message, Throwable cause) {
		super(message, cause);
	}

}