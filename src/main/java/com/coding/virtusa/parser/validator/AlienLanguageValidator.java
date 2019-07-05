package com.coding.virtusa.parser.validator;

import java.util.function.Predicate;
//glob is I
public class AlienLanguageValidator implements Predicate<String> {

	@Override
	public boolean test(String t) {
		return t.matches("\\b[a-zA-Z]+\\sis\\s(I|V|X|L|C|D|M)\\b");
		//
	}
	
	

}
