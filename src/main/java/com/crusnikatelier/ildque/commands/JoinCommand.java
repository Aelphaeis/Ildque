package com.crusnikatelier.ildque.commands;

import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;
import com.crusnikatelier.ildque.exceptions.IldqueException;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.MissingPermissionsException;

public class JoinCommand implements BotCommand {
	private static final Logger logger = LoggerFactory.getLogger(JoinCommand.class);
	Options options;
	
	public JoinCommand(){
		options = new Options();
		options.addOption(new Option("", "Joins the channel the requester is in"));
		logger.trace("init complete");
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
	public void execute(Event event, String[] argv) throws IldqueException {
		MessageReceivedEvent evt =(MessageReceivedEvent)event;
		IUser sender = evt.getMessage().getAuthor();
		List<IVoiceChannel> channels = sender.getConnectedVoiceChannels();
		handleRequest(channels);
	}
	
	void handleRequest(List<IVoiceChannel> channels) throws IldqueException{
		if(channels.isEmpty()){
			String msg = "Unable to find requester in voice channel";
			throw new IldqueException(msg);
		}
		
		if(channels.size() > 1){
			String msg = "User appears to be in two channels";
			throw new IldqueException(msg);
		}
		
		try {
			IVoiceChannel vChan = channels.get(0);
			vChan.join();
		} 
		catch (MissingPermissionsException e) {
			String msg = "Unable to join voice channel : insufficent permissions";
			throw new IldqueException(msg);
		}
	}
}
