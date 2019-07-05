package com.coding.virtusa.exceptions;

public class ParsingException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ParsingException(String message) {		
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
}
