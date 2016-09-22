package com.crusnikatelier.ildque;

import org.apache.commons.cli.OptionGroup;

import sx.blah.discord.api.IDiscordClient;

public interface BotCommand {
	String getName();
	OptionGroup getOptions();
	void execute(IDiscordClient caller, String[] argv);
}
