package com.tabular.tabular.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Customer")  // 404
public class NoSuchCustomerException extends RuntimeException {

	public NoSuchCustomerException(String message) {
		super(message);
	}

	public NoSuchCustomerException(String message, Throwable cause) {
		super(message, cause);
	}

}