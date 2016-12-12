package com.crusnikatelier.ildque.commands;

import java.util.List;

import org.apache.commons.cli.OptionGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class UsersCommand implements BotCommand {
	
	Logger logger = LoggerFactory.getLogger(UsersCommand.class);
	
	private static final String responseFormat = "%20s --- %20s --- %20s";

	@Override
	public String getName() {
		return "users";
	}

	@Override
	public OptionGroup getOptions() {
		OptionGroup options = new OptionGroup();
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		//Just check to make sure that a cast won't fail
		if(!(event instanceof MessageReceivedEvent)){
			String err = "Unable to process command because event is not of type %s";
			err = String.format(err, MessageReceivedEvent.class.getName());
			logger.error(err);
			return;
		}
		
		//Get all users who can read channel
		MessageReceivedEvent msgEvent = (MessageReceivedEvent)event;
		IMessage msg = msgEvent.getMessage();
		IChannel chan = msg.getChannel();
		List<IUser> presentUsers = chan.getUsersHere();
		
		//Build response
		StringBuilder responseBuilder = new StringBuilder("```");
		for(IUser user : presentUsers){
			String line = String.format(responseFormat, user.getName(), "", user.getID());
			responseBuilder.append(line);	
			responseBuilder.append("\n");
		}
		responseBuilder.append("```");
		
		//Send response back
		try {
			chan.sendMessage(responseBuilder.toString());
		} 
		catch (MissingPermissionsException | RateLimitException | DiscordException e) {
			logger.error("Unable to send command response", e);
		}
	}
}
