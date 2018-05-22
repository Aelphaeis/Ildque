package com.cruat.ildque.bot.commands;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.Option;

import com.cruat.ildque.bot.exceptions.IldqueException;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.MissingPermissionsException;

public class Join extends Command{

	public Join() {
		opts.addOption(new Option("", "Joins the channel the requester is in"));
	}

	@Override
	public void execute(MessageReceivedEvent event, String[] argv)
			throws IldqueException {
		
		IUser sender = event.getMessage().getAuthor();
		IGuild guild = event.getGuild();

		List<IVoiceChannel> channels = guild.getVoiceChannels()
			.stream()
			.filter(p-> p.getUsersHere().contains(sender))
			.collect(Collectors.toList());
		
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
