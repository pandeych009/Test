package com.coding.virtusa.client;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coding.virtusa.builder.MessageBuilder;
import com.coding.virtusa.parser.MessageParser;

public class MessageParserImpl {
	private static final Logger LOGGER = LogManager.getLogger(MessageParserImpl.class);
	public void populateMessage(List<String> messages) {
		messages.forEach(input -> {
			MessageParser parser = MessageBuilder.getMessageParserByMessage(input);
			if(parser == null) {
				LOGGER.debug(() -> "No parser present for message: "+input);
			}else {
				parser.parseInput(input);
			}
			
		});
	}

}
