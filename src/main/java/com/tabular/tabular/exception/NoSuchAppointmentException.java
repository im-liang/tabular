package com.tabular.tabular.exception;

/**
 *
 */
public class NoSuchAppointmentException extends RuntimeException {

	public NoSuchAppointmentException(String message) {
		super(message);
	}

	public NoSuchAppointmentException(String message, Throwable cause) {
		super(message, cause);
	}

}