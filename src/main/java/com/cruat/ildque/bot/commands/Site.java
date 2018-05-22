package com.cruat.ildque.bot.commands;

import com.cruat.ildque.bot.utilities.DiscordHelper;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Site extends Command {

	public static final String ILDQUE_LINK = "http://ildque.cruat.com";
	
	@Override
	public void execute(MessageReceivedEvent event, String[] argv) {
		String message = "<" + ILDQUE_LINK + ">";
		DiscordHelper.sendMessage(event, message);
	}
}
