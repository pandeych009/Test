package com.coding.virtusa.parser.validator;

import java.util.function.Predicate;

public class IntergalacticCreditValidator implements Predicate<String>{
	//glob glob Silver is 34 Credits
	@Override
	public boolean test(String t) {
		return t.matches("[a-zA-Z ]+\\sis\\s[0-9]+\\sCredits");
	}
}
