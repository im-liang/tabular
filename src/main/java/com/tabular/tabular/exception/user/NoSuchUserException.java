package com.tabular.tabular.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such User")  // 404
public class NoSuchUserException extends RuntimeException {

	public NoSuchUserException(String message) {
		super(message);
	}

	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

}