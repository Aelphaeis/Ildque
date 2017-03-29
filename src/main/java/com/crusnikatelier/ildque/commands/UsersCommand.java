package com.crusnikatelier.ildque.commands;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;
import com.crusnikatelier.utilities.DiscordHelper;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Presences;

public class UsersCommand implements BotCommand {
	
	Logger logger = LoggerFactory.getLogger(UsersCommand.class);
	
	private static final String responseFormat = " %-20s --- %-20s ";
	private static final String headerFormat = String.format(responseFormat, "Id", "User");
	private static final String separator = String.format(responseFormat, "--------------------", "--------------------");
	
	@Override
	public String getName() {
		return "users";
	}

	@Override
	public Options getOptions() {
		Options options = new Options();
		options.addOption(new Option("", "Displays users in room"));
		options.addOption(new Option("o", "offline", false, "Displays only offline "));
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		//Get all users who can read channel
		MessageReceivedEvent msgEvent = (MessageReceivedEvent)event;
		IMessage msg = msgEvent.getMessage();
		IChannel chan = msg.getChannel();
		
		List<IUser> usersToDisplay = null;
		try{
			CommandLineParser parser = new DefaultParser();
			CommandLine line = parser.parse(getOptions(), argv);
			
			usersToDisplay = chan.getUsersHere();
			
			if(line.hasOption('o')){
				usersToDisplay = usersToDisplay.stream().
					filter(p -> p.getPresence() == Presences.OFFLINE).
					collect(Collectors.toList());
			}
		}
		catch(ParseException e){
			logger.error("Unable to parse arguments", e);
		}
		
		//Build response
		String response = formatUserString(usersToDisplay);
		
		//Send response back
		DiscordHelper.sendMessage(chan, response);
	}
	
	private String formatUserString(List<IUser> users){
		//Build response
		StringBuilder responseBuilder = new StringBuilder("```");
		responseBuilder.append(headerFormat + "\n");
		responseBuilder.append(separator + "\n");
		for(IUser user : users){
			String line = String.format(responseFormat, user.getID(), user.getName());
			responseBuilder.append(line);	
			responseBuilder.append("\n");
		}
		responseBuilder.append("```");
		return responseBuilder.toString();
	}
}
