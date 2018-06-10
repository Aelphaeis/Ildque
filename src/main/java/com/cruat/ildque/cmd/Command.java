package com.cruat.ildque.cmd;

import org.apache.commons.cli.Options;

import com.cruat.ildque.BotCommand;
import com.cruat.ildque.Ildque;

public abstract class Command implements BotCommand{

	final Options opts = new Options();
	private final String name = getClass().getSimpleName().toLowerCase();
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
	public Ildque getContext() {
		return bot;
	}
}
