package com.tabular.tabular.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Owner")  // 404
public class NoSuchOwnerException extends RuntimeException {

	public NoSuchOwnerException(String message) {
		super(message);
	}

	public NoSuchOwnerException(String message, Throwable cause) {
		super(message, cause);
	}

}