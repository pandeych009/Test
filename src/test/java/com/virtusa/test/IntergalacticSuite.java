package com.virtusa.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.coding.virtusa.MessageTypes;
import com.coding.virtusa.builder.MessageBuilder;
import com.coding.virtusa.client.MessageParserImpl;
import com.coding.virtusa.exceptions.ParsingException;
import com.coding.virtusa.parser.MessageParser;

public class IntergalacticSuite {
	private static final Logger LOGGER = LogManager.getLogger(IntergalacticSuite.class);
	//private IntergalacticCache interGalacticCache = new IntergalacticCache<>();	
	
	public static void main(String[] args) throws ParsingException {
		IntergalacticSuite t = new IntergalacticSuite();
		t.buildCacheFromMessages();
	}
	
	@Test
	public void buildCacheFromMessages() throws ParsingException {
		
		
		test();
		
	}

	private void test() {
		List<String> messages = new ArrayList<>();
		messages.add("glob is I");
		messages.add("prok is V");
		messages.add("pish is X");
		messages.add("tegj is L");
		
		messages.add("glob glob Silver is 34 Credits");
		messages.add("glob prok Gold is 57800 Credits");
		messages.add("pish pish Iron is 3910 Credits");
			
		MessageParserImpl impl = new MessageParserImpl();
		impl.populateMessage(messages);
		
		List<String> queries = new ArrayList<>();
		queries.add("how much is pish tegj glob glob ?");
		queries.add("how many Credits is glob prok Silver ?");
		queries.add("how many Credits is glob prok Gold ?");
		queries.add("how many Credits is glob prok Iron ?");
		queries.add("MMM");
		queries.add("MMMM");
		queries.add("how many Credits is glob prok Silver ?");
		queries.forEach(message -> {
				MessageBuilder.generateResponseMessage(message);			
		});
	}
	
	@Test
	public void testRomanNumaralConversionTest() {
		String str = "MCMXLIV";
		MessageParser<Integer> parser = MessageBuilder.messageParserSelector(MessageTypes.ROMAN_TO_HUMAN_REDABLE);
		int output = parser.parseInput(str);
		assertEquals(output, 1944);
	}
	
	@Test
	public void testIntergalacticToRomanConversionTest() {
		String str = "MCMXLIV";
		MessageParser<Integer> parser = MessageBuilder.messageParserSelector(MessageTypes.ROMAN_TO_HUMAN_REDABLE);
		int output = parser.parseInput(str);
		assertEquals(output, 1944);
	}

}
