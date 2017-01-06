package com.crusnikatelier.ildque.commands.dnest;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;

public class DNestNoticeCommand implements BotCommand{

	@Override
	public String getName() {
		return "dnestnotice";
	}

	@Override
	public Options getOptions() {
		Options options = new Options();
		options.addOption(new Option("s", "subscribe", false, "Subscribe to dragon nest notices"));
		options.addOption(new Option("u", "unsubscribe", false, "Unsubscribe to dragon nest notces"));
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		// TODO Auto-generated method stub
		
	}
}
