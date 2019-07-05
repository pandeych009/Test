package com.coding.virtusa.builder;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.virtusa.MessageTypes;
import com.coding.virtusa.cache.IntergalacticCache;
import com.coding.virtusa.exceptions.ParsingException;
import com.coding.virtusa.parser.AlienLanguageToInterGalacticConversion;
import com.coding.virtusa.parser.IntergalacticToRomanNumber;
import com.coding.virtusa.parser.MessageParser;
import com.coding.virtusa.parser.RomanNumralToHumanNumber;
import com.coding.virtusa.responses.AlienLanguageResponder;
import com.coding.virtusa.responses.IntergalacticLanguageResponder;
import com.coding.virtusa.responses.ResponseGenerator;

@SuppressWarnings("rawtypes")
public class MessageBuilder {
	private static final Logger LOGGER = LogManager.getLogger(MessageBuilder.class);
	
	private static IntergalacticCache intergalacticCache = new IntergalacticCache<>(); 
	
	public static MessageParser messageParserSelector(MessageTypes messageType) {
		MessageParser messageParser = null;
		switch (messageType) {
		case ROMAN_TO_HUMAN_REDABLE:
			messageParser = RomanNumralToHumanNumber.getInstance();
			break;
		case INTERGALACTIC_TO_ROMAN:
			messageParser = IntergalacticToRomanNumber.getInstance(intergalacticCache);
			break;
		case ALIEN_CREDIT:
			messageParser = AlienLanguageToInterGalacticConversion.getInstance(intergalacticCache);
			break;

		default:
			break;
		}
		return messageParser;
	}
	
	
	public static ResponseGenerator getResponseGeneratorByMessage(String message) throws ParsingException {
		ResponseGenerator responseGenerator = null;
		if(message.matches("how\\smuch\\sis\\s[a-zA-Z0-9 ]+\\?")) {
			responseGenerator = AlienLanguageResponder.getInstance(intergalacticCache);
		}else if(message.matches("how\\smany\\sCredits\\sis\\s[a-zA-Z0-9 ]+\\?")) {
			responseGenerator = new IntergalacticLanguageResponder(intergalacticCache);
		}
		
		return responseGenerator;
	}
	
	
	public static MessageParser getMessageParserByMessage(String message) {
		String[] messageSplits = MessageParser.PATTERN.split(message);
		int messageLength = messageSplits.length;
		if(!message.contains(MessageParser.QUESTION)) {
			if(message.contains("is") && messageLength == 3 && StringUtils.containsAny(messageSplits[2], MessageParser.APPLICABLE_ROMAN_NUMBER)) {
				return messageParserSelector(MessageTypes.INTERGALACTIC_TO_ROMAN);
			}else if(message.contains("Credits")) {
				return messageParserSelector(MessageTypes.ALIEN_CREDIT);
			}else if(StringUtils.containsAny(message, MessageParser.APPLICABLE_ROMAN_NUMBER)) {
				return messageParserSelector(MessageTypes.ROMAN_TO_HUMAN_REDABLE);
			}
		}
				
		return null;
	}
	
	
	public static void generateResponseMessage(String message) {
		
		int humanNumber ;
		MessageParser parser = getMessageParserByMessage(message);
		if(Objects.nonNull(parser)) {
			humanNumber = (int) parser.parseInput(message);
			LOGGER.info(() -> message +"\t converted to : \t"+humanNumber);			
		}else {
			try {
				ResponseGenerator generator = getResponseGeneratorByMessage(message);
				LOGGER.info(() -> message +"\t converted to : \t"+generator.prepareResponse(message));		
			} catch (ParsingException e) {
				final String response = "I have no idea what you are talking about: for message: "+message;
				LOGGER.info(() -> message +"\t converted to : \t"+response);
			}
		}
	}
}
