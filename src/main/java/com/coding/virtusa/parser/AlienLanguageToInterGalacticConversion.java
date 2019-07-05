package com.coding.virtusa.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.virtusa.cache.IntergalacticCache;
import com.coding.virtusa.exceptions.ParsingException;
import com.coding.virtusa.modals.CreditModal;
import com.coding.virtusa.validator.IntergalacticValidator;

@SuppressWarnings("rawtypes")
public class AlienLanguageToInterGalacticConversion implements MessageParser<CreditModal> {
	private static final Logger LOGGER = LogManager.getLogger(IntergalacticToRomanNumber.class);

	private static int PRODUCT_INDEX = 2;
	private static int PRODUCT_VALUE_INDEX = 4;

	private static MessageParser<CreditModal> _INSTANCE = null;

	public static MessageParser<CreditModal> getInstance(IntergalacticCache cache) {
		if(_INSTANCE == null) {
			_INSTANCE = new AlienLanguageToInterGalacticConversion(cache);
		}
		return _INSTANCE;
	}


	private IntergalacticCache cache;
	private AlienLanguageToInterGalacticConversion(IntergalacticCache cache) {
		this.cache=cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CreditModal parseInput(String input) {
		String[] splits = PATTERN.split(input);
		try {
			IntergalacticValidator.validateIntergalacticCredit(input);
		} catch (ParsingException e) {
			LOGGER.error(() -> " "+e.getMessage());
			throw new RuntimeException(e.getMessage());			
		}

		String product = splits[PRODUCT_INDEX];
		int creditValue = Integer.parseInt(splits[PRODUCT_VALUE_INDEX]);

		String AlienNumberToRomanNumber = cache.fetchFromCache(splits[0]).toString().concat(cache.fetchFromCache(splits[1]).toString());

		int romanNumberToHumanReadableNumber = RomanNumralToHumanNumber.getInstance().parseInput(AlienNumberToRomanNumber);
		int valueOfProduct = creditValue/romanNumberToHumanReadableNumber;

		CreditModal modal = new CreditModal(product, valueOfProduct);
		cache.addInCache(product, modal);
		return modal;
	}

}
