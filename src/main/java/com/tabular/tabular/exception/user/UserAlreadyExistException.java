package com.tabular.tabular.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="User already exist")  // 409
public class UserAlreadyExistException extends RuntimeException {

	public UserAlreadyExistException(String message) {
		super(message);
	}

	public UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}