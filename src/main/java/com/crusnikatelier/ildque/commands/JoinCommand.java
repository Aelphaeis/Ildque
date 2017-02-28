package com.crusnikatelier.ildque.commands;

import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class JoinCommand implements BotCommand {

	private static final Logger logger = LoggerFactory.getLogger(JoinCommand.class);
	Options options;
	
	public JoinCommand(){
		options = new Options();
		options.addOption(new Option("", "Joins the channel the requester is in"));
	}
	
	@Override
	public String getName() {
		return "join";
	}

	@Override
	public Options getOptions() {
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		
		MessageReceivedEvent evt =(MessageReceivedEvent)event;
		IUser sender = evt.getMessage().getAuthor();
		List<IVoiceChannel> channels = sender.getConnectedVoiceChannels();

		try{
			handleRequest(channels);
		}
		catch(IllegalStateException e){
			try {
				IChannel chan = evt.getMessage().getChannel();
				chan.sendMessage(e.getMessage());
			} 
			catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
				logger.error("Unable to send error message to user", e);
			}
		}
	}
	
	void handleRequest(List<IVoiceChannel> channels){
		
		if(channels.isEmpty()){
			String msg = "Unable to find requester in voice channel";
			throw new IllegalStateException(msg);
		}
		
		if(channels.size() > 1){
			String msg = "User appears to be in two channels";
			throw new IllegalStateException(msg);
		}
		
		try {
			IVoiceChannel vChan = channels.get(0);
			vChan.join();
		} 
		catch (MissingPermissionsException e) {
			String msg = "Unable to join voice channel : insufficent permissions";
			throw new IllegalStateException(msg, e);
		}
	}

}
