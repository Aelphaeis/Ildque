package com.crusnikatelier.ildque.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.Bot;
import com.crusnikatelier.ildque.BotCommand;
import com.crusnikatelier.ildque.BotCommandTextHandler;
import com.crusnikatelier.ildque.BotSpecialCommand;
import com.crusnikatelier.utilities.DiscordHelper;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

public class HelpCommand implements BotSpecialCommand{
	Logger logger = LoggerFactory.getLogger(HelpCommand.class);
	private static int DEFAULT_WIDTH = 100;

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
		if(!(event instanceof MessageReceivedEvent)){
			String err = "Unable to process command because event is not of type %s";
			err = String.format(err, MessageReceivedEvent.class.getName());
			logger.error(err);
			return;
		}
		
		try {
			MessageReceivedEvent evt = (MessageReceivedEvent) event;
			CommandLineParser parser = new DefaultParser();
			CommandLine line  = parser.parse(getOptions(), argv);
			
			if(isDefaultHelpRequest(line)){
				displayGeneralHelp(handler, evt);
			}
		}
		catch (ParseException e) {
			logger.error("Unable to parse arguments", e);
		}
	}
	
	private boolean isDefaultHelpRequest(CommandLine line){
		boolean result = true;
		result = result & (line.getOptions().length == 0);
		return result;
	}
	
	
	private void displayGeneralHelp(BotCommandTextHandler handler, MessageReceivedEvent event){
		List<BotCommand> commands = handler.getCommands();
		HelpFormatter formatter = new HelpFormatter();
		
		StringBuilder builder = new StringBuilder("```");
		
		for(BotCommand cmd : commands){
			StringWriter sw = new StringWriter();
			PrintWriter writer = new PrintWriter(sw);
			
			String usage = handler.getPrefix() + cmd.getName();
			Options opts = cmd.getOptions();
			
			formatter.printHelp(writer, DEFAULT_WIDTH, usage , "", opts, 7, 2, "");
			
			String formatted = sw.toString();
			builder.append(formatted);
		}
		builder.append("```");
		DiscordHelper.sendMessage(event, builder.toString());
	}
}
