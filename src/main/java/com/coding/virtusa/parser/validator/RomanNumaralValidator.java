package com.coding.virtusa.parser.validator;

import java.util.function.Predicate;

public class RomanNumaralValidator implements Predicate<String>{
	@Override
	public boolean test(String test) {
		return test.matches("\\bM{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})\\b");
	}
}


