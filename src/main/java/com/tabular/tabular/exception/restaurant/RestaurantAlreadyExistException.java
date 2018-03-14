package com.tabular.tabular.exception.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Restaurant already exist")  // 409
public class RestaurantAlreadyExistException extends RuntimeException {

	public RestaurantAlreadyExistException(String message) {
		super(message);
	}

	public RestaurantAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}