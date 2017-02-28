package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.Options;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;

public class JoinCommand implements BotCommand {

	Options options;
	
	public JoinCommand() {
		options = new Options();
		options.addOption("u", true, "link of discord server to join");
	}
	
	@Override
	public String getName() {
		return "join";
	}

	@Override
	public Options getOptions() {
		Options options = new Options();
		options.addOption("u", true, "link of discord server to join");
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		// TODO Auto-generated method stub
		
	}

}
