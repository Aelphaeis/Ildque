package com.cruat.ildque.bot.cmd;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.bot.BotCommand;
import com.cruat.ildque.bot.util.DiscordHelper;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Help extends Command {
	
	private static final int DEFAULT_WIDTH = 100;
	private static final Logger logger = LogManager.getLogger();
	private static final String USAGE_FORMAT = "%s %s";

	@Override
	public void execute(MessageReceivedEvent event, String[] argv) {
		try {
			CommandLineParser parser = new DefaultParser();
			CommandLine line  = parser.parse(getOptions(), argv);
			
			if(isDefaultHelpRequest(line)){
				displayGeneralHelp(event);
			}
		}
		catch (ParseException e) {
			logger.error("Unable to parse arguments", e);
		}
	}
	
	private boolean isDefaultHelpRequest(CommandLine line){
		return line.getOptions().length == 0;
	}
	
	private void displayGeneralHelp(MessageReceivedEvent event){
		
		List<BotCommand> commands = getContext().getHandler().getCommands();
		HelpFormatter formatter = new HelpFormatter();
		
		StringBuilder builder = new StringBuilder("```");
		String prefix = getContext().getConfiguration().getPrefix();
		for(BotCommand cmd : commands){
			StringWriter sw = new StringWriter();
			PrintWriter writer = new PrintWriter(sw);

			
			String usage = String.format(USAGE_FORMAT, prefix, cmd.getName());
			Options opts = cmd.getOptions();
			
			formatter.printHelp(writer, DEFAULT_WIDTH, usage , "", opts, 7, 2, "");
			
			String formatted = sw.toString();
			builder.append(formatted);
		}
		builder.append("```");
		DiscordHelper.sendMessage(event, builder.toString());
	}
}
