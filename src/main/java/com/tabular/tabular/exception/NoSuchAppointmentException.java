package com.tabular.tabular.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Appointment")  // 404
public class NoSuchAppointmentException extends RuntimeException {

	public NoSuchAppointmentException(String message) {
		super(message);
	}

	public NoSuchAppointmentException(String message, Throwable cause) {
		super(message, cause);
	}

}