package com.tabular.tabular.exception.table;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Table")  // 404
public class NoSuchTableException extends RuntimeException {

	public NoSuchTableException(String message) {
		super(message);
	}

	public NoSuchTableException(String message, Throwable cause) {
		super(message, cause);
	}

}