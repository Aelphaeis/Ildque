package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

public class LinkCommand implements BotCommand {
	
	private static final Logger logger = LoggerFactory.getLogger(LinkCommand.class);

	Options options;
	
	public LinkCommand() {
		options = new Options();
		options.addOption("", true, "Show Authorization Link for bot");
	}
	
	@Override
	public String getName() {
		return "link";
	}

	@Override
	public Options getOptions() {
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		if(!(event instanceof MessageReceivedEvent)){
			String err = "Unable to process command because event is not of type %s";
			err = String.format(err, MessageReceivedEvent.class.getName());
			logger.error(err);
			return; 
		}
		// TODO Auto-generated method stub
		
	}
	
	

}
