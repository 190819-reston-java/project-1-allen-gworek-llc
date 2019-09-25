package com.revature.exception;

public class InvalidArgumentFormatException extends RuntimeException {

	public InvalidArgumentFormatException() {
		this("Did not pass in the correct format of alternating String, Object format!");
	}
	
	public InvalidArgumentFormatException(String message) {
		super(message);
	}
}
