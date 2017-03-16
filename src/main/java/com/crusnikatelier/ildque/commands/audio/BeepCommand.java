package com.crusnikatelier.ildque.commands.audio;

import org.apache.commons.cli.Options;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;

public class BeepCommand implements BotCommand{

	@Override
	public String getName() {
		return "beep";
	}

	@Override
	public Options getOptions() {
		Options options = new Options();
		options.addOption("", "Plays beep");
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		
		
	}

}
