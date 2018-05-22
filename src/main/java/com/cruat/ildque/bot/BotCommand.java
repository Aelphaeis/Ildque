package com.cruat.ildque.bot;

import org.apache.commons.cli.Options;

import com.cruat.ildque.bot.exceptions.CommandException;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public interface BotCommand {
	String getName();
	Options getOptions();
	void setContext(Ildque context);
	Ildque getContext();
	/**
	 * @param e object representing the message received
	 * @param argv parsed arguments from the message received content. This 
	 * argument can never be null or empty. The first element will always equal
	 * the name of the command (case insensitive)
	 * @throws CommandException
	 */
	void execute(MessageReceivedEvent e, String[] argv) throws CommandException;
}
