package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.util.DiscordException;

public class TerminateCommand implements BotCommand {

	private static final Logger logger = LoggerFactory.getLogger(TerminateCommand.class);
	
	@Override
	public String getName() {
		return "terminate";
	}
	
	@Override
	public Options getOptions() {
		Options options = new Options();
		options.addOption("", "terminates the bot");
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		try {
			IDiscordClient client = event.getClient();
			client.logout();
			
			logger.info("successfully logged out of discord");
		} 
		catch (DiscordException e) {
			logger.error("Unable to terminate bot", e);
		}
		logger.info("Terminating self");
	}
}
