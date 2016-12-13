package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.Bot;
import com.crusnikatelier.ildque.BotCommandTextHandler;
import com.crusnikatelier.ildque.BotSpecialCommand;

import sx.blah.discord.api.events.Event;

public class HelpCommand implements BotSpecialCommand{
	Logger logger = LoggerFactory.getLogger(HelpCommand.class);

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public Options getOptions() {
		return new Options();
	}

	@Override
	public void execute(Bot bot, BotCommandTextHandler handler, Event event, String[] argv) {
		try {
			CommandLineParser parser = new DefaultParser();
			CommandLine line  = parser.parse(getOptions(), argv);
			
			if(isDefaultHelpRequest(line)){
			}

			logger.info(argv.length + " arguments found");
			logger.info(line.getArgs().length + " arguments not parsed ");
			
		}
		catch (ParseException e) {
			logger.error("Unable to parse arguments", e);
		}
	}
	
	private boolean isDefaultHelpRequest(CommandLine line){
		boolean result = true;

		String[] args = line.getArgs();
	
		result = result & (line.getOptions().length == 0);
		result = result & ((args.length == 1) && args[0].equals(getName()));

		return result;
	}

}
