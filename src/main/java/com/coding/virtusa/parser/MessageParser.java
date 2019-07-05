package com.coding.virtusa.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@FunctionalInterface
public interface MessageParser<T> {
	
	public static final String APPLICABLE_ROMAN_NUMBER = "MDCLXVI"; 
	public static final Map<Character, Integer> ROMAN_NUMBER_MAP_BY_VALUE = new HashMap<Character, Integer>() {
		{
			put('M', 1000);
			put('D', 500);
			put('C', 100);
			put('L', 50);
			put('X', 10);
			put('V', 5);
			put('I', 1);

		}
	};
	
	public static final String QUESTION = "?";
	public static final String SEPARATOR = "\\s";
	public static final Pattern PATTERN = Pattern.compile(SEPARATOR);
	
	public default int getMaxRomanNumber() {
		return ROMAN_NUMBER_MAP_BY_VALUE.get('M');
	}
	
	public default int getHumanNumberByRomanNumaral(char element) {
		return ROMAN_NUMBER_MAP_BY_VALUE.get(element);
	}
	
	public default String getRomanNumber(String key) {
		return key;
	}
	
	public T parseInput(String input);
	
}
