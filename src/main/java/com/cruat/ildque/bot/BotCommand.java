package com.cruat.ildque.bot;

import org.apache.commons.cli.Options;

import com.cruat.ildque.bot.exceptions.IldqueException;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public interface BotCommand {
	String getName();
	Options getOptions();
	void execute(MessageReceivedEvent event, String[] argv) throws IldqueException;
}
