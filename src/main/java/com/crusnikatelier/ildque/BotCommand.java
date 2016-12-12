package com.crusnikatelier.ildque;

import org.apache.commons.cli.OptionGroup;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.Event;

public interface BotCommand {
	String getName();
	OptionGroup getOptions();
	void execute(Event event, String[] argv);
}
