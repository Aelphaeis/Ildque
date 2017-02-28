package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class LinkCommand implements BotCommand {
	
	private static final Logger logger = LoggerFactory.getLogger(LinkCommand.class);
	private static final String BASE_URL = "https://discordapp.com/api/oauth2/authorize";
	
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
		try {	
			StringBuffer buffer = new StringBuffer(BASE_URL);
			buffer.append("?client_id=");
			buffer.append(event.getClient().getApplicationClientID());
			buffer.append("&scope=bot");
			
			MessageReceivedEvent mrEvt = (MessageReceivedEvent)event;
			IChannel chan = mrEvt.getMessage().getChannel();
			chan.sendMessage(buffer.toString());
			
		}
		catch (DiscordException | MissingPermissionsException | RateLimitException e) {
			String msg = "Unable to send authorization link";
			logger.error(msg, e);
		}
	}
}
