package com.coding.virtusa.responses;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.virtusa.cache.IntergalacticCache;
import com.coding.virtusa.exceptions.ParsingException;
import com.coding.virtusa.modals.CreditModal;
import com.coding.virtusa.parser.IntergalacticToRomanNumber;
import com.coding.virtusa.parser.MessageParser;
import com.coding.virtusa.parser.RomanNumralToHumanNumber;
import com.coding.virtusa.validator.IntergalacticValidator;

@SuppressWarnings("rawtypes")
public class IntergalacticLanguageResponder implements ResponseGenerator {
	private static final Logger LOGGER = LogManager.getLogger(IntergalacticLanguageResponder.class);
	
	private IntergalacticCache intergalacticCache;
	
	public IntergalacticLanguageResponder(IntergalacticCache intergalacticCache) {
		this.intergalacticCache = intergalacticCache;
	}


	@Override
	public String prepareResponse(String query) {
		String responseFormat = "%s %s %s is %d Credits";
		
		String[] splits = MessageParser.PATTERN.split(query);
		try {
			IntergalacticValidator.validateIntergalacticQueryValidation(query);;
		} catch (ParsingException e) {
			LOGGER.error(() -> "I have no idea what you are talking about:  "+e.getMessage());
			throw new RuntimeException("I have no idea what you are talking about:  ");
		}

		//how many Credits is glob prok Silver ?
		String product = splits[6];
		@SuppressWarnings("unchecked")
		CreditModal modal = (CreditModal) intergalacticCache.fetchFromCache(product);
		
		String[] alienLangKeyWord = {splits[4], splits[5]};
		
		String romanNumber = Arrays.stream(alienLangKeyWord).map(input -> IntergalacticToRomanNumber.getInstance(intergalacticCache).getRomanNumber(input)).reduce("", (a, b) -> a.concat(b));
		
		int humanRedableNumberFromRomanNumber = RomanNumralToHumanNumber.getInstance().parseInput(romanNumber);
		int totalCredit = modal.getValue()*humanRedableNumberFromRomanNumber;
		
		String response = String.format(responseFormat, alienLangKeyWord[0], alienLangKeyWord[1], product, totalCredit);
		return response;
	}

}
