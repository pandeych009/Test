package com.coding.virtusa.parser;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.virtusa.exceptions.ParsingException;
import com.coding.virtusa.validator.IntergalacticValidator;

public class RomanNumralToHumanNumber implements MessageParser<Integer> {
	
	private static final Logger LOGGER = LogManager.getLogger(RomanNumralToHumanNumber.class);
	
	private static final MessageParser<Integer> _ROMAN_NUMBER_CONVERTOR_INSTANCE = new RomanNumralToHumanNumber();
	
	public static MessageParser<Integer> getInstance() {
		return _ROMAN_NUMBER_CONVERTOR_INSTANCE;
	}
	
	@Override
	public Integer parseInput(String input) {
		
		char[] romanSplits = input.toCharArray();
		try {
			IntergalacticValidator.validateRomanNumral(input);
		}catch (ParsingException e) {
			LOGGER.error(() -> "I have no idea what you are talking about:  "+e.getMessage());
			throw new RuntimeException("I have no idea what you are talking about:  ");
		}
		
		int sum = 0, lastValue = getMaxRomanNumber(),  currentValue = 0;
		for (char romanChar : romanSplits) {
			currentValue = getHumanNumberByRomanNumaral(romanChar);
			sum = sum + currentValue;
			
			if(lastValue < currentValue) {
				sum = sum - lastValue*2;
			}
			lastValue=currentValue;
		}
		return sum;
	}
}
