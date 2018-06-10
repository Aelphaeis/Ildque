package com.cruat.ildque.bot.cmd;

import com.cruat.ildque.bot.utilities.DiscordHelper;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Site extends Command {

	public static final String ILDQUE_LINK = "http://ildque.cruat.com";
	public static final String MESSAGE = "<" + ILDQUE_LINK + ">";
	
	@Override
	public void execute(MessageReceivedEvent event, String[] argv) {
		DiscordHelper.sendMessage(event, MESSAGE);
	}
}
