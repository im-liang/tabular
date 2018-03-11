package com.tabular.tabular.exception.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Restaurant")  // 404
public class NoSuchRestaurantException extends RuntimeException {

	public NoSuchRestaurantException(String message) {
		super(message);
	}

	public NoSuchRestaurantException(String message, Throwable cause) {
		super(message, cause);
	}

}