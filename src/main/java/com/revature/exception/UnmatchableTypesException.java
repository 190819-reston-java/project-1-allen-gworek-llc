package com.revature.exception;

public class UnmatchableTypesException extends RuntimeException {

	public UnmatchableTypesException() {
		this("Can not use two different objects in this argument!");
	}
	
	public UnmatchableTypesException(String message) {
		super(message);
	}
}
