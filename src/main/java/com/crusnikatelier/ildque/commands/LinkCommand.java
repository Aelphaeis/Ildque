package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;
import com.crusnikatelier.ildque.exceptions.IldqueException;
import com.crusnikatelier.utilities.DiscordHelper;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;

public class LinkCommand implements BotCommand {
	
	private static final Logger logger = LoggerFactory.getLogger(LinkCommand.class);
	private static final String BASE_URL = "https://discordapp.com/api/oauth2/authorize";
	
	Options options;
	
	public LinkCommand() {
		options = new Options();
		options.addOption("", "Show Authorization Link for bot");
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
	public void execute(Event event, String[] argv) throws IldqueException {
		try{
			StringBuffer buffer = new StringBuffer(BASE_URL);
			buffer.append("?client_id=");
			buffer.append(event.getClient().getApplicationClientID());
			buffer.append("&scope=bot");
			
			MessageReceivedEvent mrEvt = (MessageReceivedEvent)event;
			DiscordHelper.sendMessage(mrEvt, buffer.toString());
		}
		catch(DiscordException e){
			String err = "Unable to get build url";
			logger.error(err, e);
			throw new IldqueException(err);
		}
	}
}
