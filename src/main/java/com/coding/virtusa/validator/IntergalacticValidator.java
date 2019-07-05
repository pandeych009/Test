package com.coding.virtusa.validator;

import com.coding.virtusa.exceptions.ParsingException;
import com.coding.virtusa.parser.validator.AlienLanguageValidator;
import com.coding.virtusa.parser.validator.IntergalacticCreditValidator;
import com.coding.virtusa.parser.validator.RomanNumaralValidator;
import com.coding.virtusa.responses.validator.AlienLanguageQueryValidator;
import com.coding.virtusa.responses.validator.IntergalacticToRomanValidator;

public class IntergalacticValidator {

	public static void validateRomanNumral(String input)throws ParsingException{
		Validator<String> validate = new Validator<>();
		validate.addValidator(new RomanNumaralValidator());
		if(!validate.test(input)) {
			throw new ParsingException("Invalid input provided: "+input);
		}
	}


	public static void validateIntergalacticToRomanNumral(String input) throws ParsingException {
		Validator<String> validate = new Validator<>();
		validate.addValidator(new AlienLanguageValidator());
		if(!validate.test(input)) {
			throw new ParsingException("Invalid input provided: "+input);
		}
	}
	
	public static void validateIntergalacticCredit(String input) throws ParsingException {
		Validator<String> validate = new Validator<>();
		validate.addValidator(new IntergalacticCreditValidator());
		if(!validate.test(input)) {
			throw new ParsingException("Invalid input provided: "+input);
		}
	}

	public static void validateAlienLanguageQuery(String input) throws ParsingException {
		Validator<String> validate = new Validator<>();
		validate.addValidator(new AlienLanguageQueryValidator());
		if(!validate.test(input)) {
			throw new ParsingException("Invalid input provided: "+input);
		}
	}
	
	public static void validateIntergalacticQueryValidation(String input) throws ParsingException {
		Validator<String> validate = new Validator<>();
		validate.addValidator(new IntergalacticToRomanValidator());
		if(!validate.test(input)) {
			throw new ParsingException("Invalid input provided: "+input);
		}
	}
	
	
	

}
