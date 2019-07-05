package com.coding.virtusa.responses;

import java.util.regex.Pattern;

public interface ResponseGenerator {
	
	public static final String SEPARATOR = "\\sis\\s";
	public static final Pattern PATTERN = Pattern.compile(SEPARATOR);
	
	public String prepareResponse(String query);

}
