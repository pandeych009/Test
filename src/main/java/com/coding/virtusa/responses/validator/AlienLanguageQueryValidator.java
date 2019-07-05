package com.coding.virtusa.responses.validator;

import java.util.function.Predicate;

public class AlienLanguageQueryValidator implements Predicate<String>{
	
	@Override
	public boolean test(String t) {
		return t.matches("how\\smuch\\sis\\s[a-zA-Z0-9 ]+\\?");
	}
//
}
