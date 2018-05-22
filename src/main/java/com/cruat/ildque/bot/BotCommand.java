package com.cruat.ildque.bot;

import org.apache.commons.cli.Options;

import com.cruat.ildque.bot.exceptions.CommandException;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public interface BotCommand {
	String getName();
	Options getOptions();
	void setContext(Ildque context);
	Ildque getContext();
	void execute(MessageReceivedEvent e, String[] argv) throws CommandException;
}
