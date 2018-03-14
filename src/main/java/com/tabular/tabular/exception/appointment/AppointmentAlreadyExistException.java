package com.tabular.tabular.exception.appointment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Appointment already exist")  // 409
public class AppointmentAlreadyExistException extends RuntimeException {

	public AppointmentAlreadyExistException(String message) {
		super(message);
	}

	public AppointmentAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}