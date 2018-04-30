package com.cruat.ildque.bot.commands;

import org.apache.commons.cli.Option;

import com.cruat.ildque.bot.exceptions.IldqueException;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class Leave extends Command{
	
	public Leave() {
		opts.addOption(new Option("", "Forces bot to leave all connected voice channels"));
	}

	@Override
	public void execute(MessageReceivedEvent event, String[] argv)
			throws IldqueException {
		for(IVoiceChannel channel : event.getClient().getConnectedVoiceChannels()) {
			channel.leave();
		}
	}
}
