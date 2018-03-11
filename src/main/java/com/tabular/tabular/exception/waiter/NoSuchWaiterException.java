package com.tabular.tabular.exception.waiter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Waiter")  // 404
public class NoSuchWaiterException extends RuntimeException {

	public NoSuchWaiterException(String message) {
		super(message);
	}

	public NoSuchWaiterException(String message, Throwable cause) {
		super(message, cause);
	}

}