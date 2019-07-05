package com.coding.virtusa.responses;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.virtusa.cache.IntergalacticCache;
import com.coding.virtusa.exceptions.ParsingException;
import com.coding.virtusa.parser.IntergalacticToRomanNumber;
import com.coding.virtusa.parser.MessageParser;
import com.coding.virtusa.parser.RomanNumralToHumanNumber;
import com.coding.virtusa.validator.IntergalacticValidator;

@SuppressWarnings("rawtypes")
public class AlienLanguageResponder implements ResponseGenerator {
	private static final Logger LOGGER = LogManager.getLogger(AlienLanguageResponder.class);

	private static ResponseGenerator _RESPONDER_INSTANCE = null;

	public static ResponseGenerator getInstance(IntergalacticCache intergalacticCache) {
		if(_RESPONDER_INSTANCE == null) {
			_RESPONDER_INSTANCE = new AlienLanguageResponder(intergalacticCache);
		}
		return _RESPONDER_INSTANCE;
	}
	private IntergalacticCache intergalacticCache;

	public AlienLanguageResponder(IntergalacticCache intergalacticCache) {
		this.intergalacticCache=intergalacticCache;
	}


	@Override
	public String prepareResponse(String query) {
		//how much is pish tegj glob glob ?
		String responseFormat = "%s is %d";
		
		try {
			IntergalacticValidator.validateAlienLanguageQuery(query);
		} catch (ParsingException e) {
			LOGGER.error(() -> "I have no idea what you are talking about:  "+e.getMessage());
			throw new RuntimeException("I have no idea what you are talking about:  ");
		}
		query=query.replace("?", "");
		String[] splits = PATTERN.split(query);
		String[] nestedSplits = MessageParser.PATTERN.split(splits[1]);		
		
		String romanNumber = Arrays.stream(nestedSplits).map(input -> IntergalacticToRomanNumber.getInstance(intergalacticCache).getRomanNumber(input)).reduce("", (a, b) -> a.concat(b));
		int humanRedableNumberFromRomanNumber = RomanNumralToHumanNumber.getInstance().parseInput(romanNumber);		
		String response = String.format(responseFormat, splits[1], humanRedableNumberFromRomanNumber);		
		return response;
	}

}