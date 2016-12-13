package com.crusnikatelier.ildque;

import org.apache.commons.cli.OptionGroup;

import sx.blah.discord.api.events.Event;

public interface BotCommand {
	String getName();
	OptionGroup getOptions();
	void execute(Bot bot, Event event, String[] argv);
}
