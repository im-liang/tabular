package com.tabular.tabular.exception.relation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="this relation already exist")  // 409
public class RelationAlreadyExistException extends RuntimeException {

	public RelationAlreadyExistException(String message) {
		super(message);
	}

	public RelationAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}