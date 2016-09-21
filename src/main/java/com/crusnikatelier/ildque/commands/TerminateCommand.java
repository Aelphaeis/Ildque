package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.OptionGroup;

import com.crusnikatelier.ildque.Bot;
import com.crusnikatelier.ildque.BotCommand;

public class TerminateCommand implements BotCommand {
	
	Bot bot;
	public TerminateCommand(Bot bot) {
		this.bot = bot;
	}

	@Override
	public String getName() {
		return "terminate";
	}
	
	@Override
	public OptionGroup getOptions() {
		OptionGroup options = new OptionGroup();
		return options;
	}

	@Override
	public void execute(String[] argv) {
		this.bot.logout();
		System.exit(0);
	}
}
