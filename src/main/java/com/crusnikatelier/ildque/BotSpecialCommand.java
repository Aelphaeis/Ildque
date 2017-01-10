package com.crusnikatelier.ildque;

import org.apache.commons.cli.Options;

import sx.blah.discord.api.events.Event;

/**
 * This class is used for commands that are self aware.
 * @author joseph.morain
 *
 */
public interface BotSpecialCommand {
	String getName();
	Options getOptions();
	void execute(Bot bot, BotCommandTextHandler handler, Event event, String[] argv);
}
