package com.crusnikatelier.ildque;

import org.apache.commons.cli.Options;

import com.crusnikatelier.ildque.exceptions.IldqueException;

import sx.blah.discord.api.events.Event;

public interface BotCommand {
	String getName();
	Options getOptions();
	void execute(Event event, String[] argv) throws IldqueException;
}
