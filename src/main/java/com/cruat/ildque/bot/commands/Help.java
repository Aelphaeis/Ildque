package com.cruat.ildque.bot.commands;

import com.cruat.ildque.bot.exceptions.IldqueException;
import com.cruat.ildque.bot.utilities.DiscordHelper;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Help extends Command {
	
	@Override
	public String getName() {
		return "help";
	}

	@Override
	public void execute(MessageReceivedEvent event, String[] argv) throws IldqueException {
		DiscordHelper.sendMessage(event, "help called");
	}
}
