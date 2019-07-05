package com.coding.virtusa.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.virtusa.cache.IntergalacticCache;
import com.coding.virtusa.exceptions.ParsingException;
import com.coding.virtusa.validator.IntergalacticValidator;

@SuppressWarnings("rawtypes")
public class IntergalacticToRomanNumber implements MessageParser<String> {
	private static final Logger LOGGER = LogManager.getLogger(IntergalacticToRomanNumber.class);
		
	private static MessageParser<String> _INTERGALACTIC_INSTANCE = null;
	
	@SuppressWarnings("unchecked")
	public static MessageParser<String> getInstance(IntergalacticCache cache) {
		if(_INTERGALACTIC_INSTANCE == null) {
			_INTERGALACTIC_INSTANCE = new IntergalacticToRomanNumber(cache);
		}
		return _INTERGALACTIC_INSTANCE;
	}
	
	
	private IntergalacticCache<String, String> cache;
	
	private IntergalacticToRomanNumber(IntergalacticCache<String, String> cache) {
		this.cache=cache;
	}
	
	
	@Override
	public String parseInput(String input) {
		String[] splits = PATTERN.split(input);
		try {
			IntergalacticValidator.validateIntergalacticToRomanNumral(input);;
		} catch (ParsingException e) {
			LOGGER.error(() -> " "+e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		cache.addInCache(splits[0], splits[2]);
		return null;
	}
	
	
	public String getRomanNumber(String key) {
		return cache.fetchFromCache(key);
	}
}
