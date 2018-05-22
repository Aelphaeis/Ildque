package com.cruat.ildque.bot.commands;

import org.apache.commons.cli.Options;

import com.cruat.ildque.bot.BotCommand;
import com.cruat.ildque.bot.Ildque;

public abstract class Command implements BotCommand{

	final Options opts = new Options();
	private final String name = getClass().getSimpleName();
	private Ildque bot;

	@Override
	public String getName() {
		return name;
	} 

	@Override
	public Options getOptions() {
		return opts;
	}
	
	@Override
	public void setContext(Ildque context) {
		bot = context;
	}
	
	@Override
	public Ildque getContext(Ildque context) {
		return bot;
	}
}
