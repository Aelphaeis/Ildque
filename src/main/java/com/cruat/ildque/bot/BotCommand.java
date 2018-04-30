package com.cruat.ildque.bot;

import org.apache.commons.cli.Options;
import com.cruat.ildque.bot.exceptions.IldqueException;
import sx.blah.discord.api.events.Event;

public interface BotCommand {
	String getName();
	Options getOptions();
	void execute(Event event, String[] argv) throws IldqueException;
}
