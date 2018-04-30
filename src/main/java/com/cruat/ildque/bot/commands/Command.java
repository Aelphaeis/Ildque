package com.cruat.ildque.bot.commands;

import org.apache.commons.cli.Options;

import com.cruat.ildque.bot.BotCommand;

public abstract class Command implements BotCommand{

	final Options opts = new Options();
	private final  String name = getClass().getSimpleName();

	@Override
	public String getName() {
		return name;
	} 

	@Override
	public Options getOptions() {
		return opts;
	}
}
