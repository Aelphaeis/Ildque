package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.Options;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;

public class JoinCommand implements BotCommand {

	Options options;
	
	public JoinCommand(){
		options = new Options();
	}
	
	@Override
	public String getName() {
		return "join";
	}

	@Override
	public Options getOptions() {
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		// TODO Auto-generated method stub

	}

}
