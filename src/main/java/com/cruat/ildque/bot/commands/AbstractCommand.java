package com.cruat.ildque.bot.commands;

import org.apache.commons.cli.Options;

import com.cruat.ildque.bot.BotCommand;

public abstract class AbstractCommand implements BotCommand{

	final Options options = new Options();
	private final  String name = getClass().getSimpleName();

	@Override
	public String getName() {
		return name;
	} 

	@Override
	public Options getOptions() {
		return options;
	}
}
