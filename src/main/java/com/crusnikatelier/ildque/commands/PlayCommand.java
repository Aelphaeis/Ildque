package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;

public class PlayCommand implements BotCommand{

	Options opts;
	public PlayCommand(){
		opts = new Options();
		opts.addOption(new Option("u", "url", true, "link to play (Only supports youtube)" ));
	}
	@Override
	public String getName() {
		return "play";
	}

	@Override
	public Options getOptions() {
		return opts;
	}

	@Override
	public void execute(Event event, String[] argv) {
		// TODO Auto-generated method stub
		
	}

}
