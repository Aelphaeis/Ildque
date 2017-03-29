package com.crusnikatelier.ildque.commands;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import com.crusnikatelier.ildque.BotCommand;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class LeaveCommand implements BotCommand{

	Options opts;
	
	public LeaveCommand(){
		opts = new Options();
		opts.addOption(new Option("", "Forces bot to leave all connected voice channels"));
	}
	
	@Override
	public String getName() {
		return "leave";
	}

	@Override
	public Options getOptions() {
		return opts;
	}

	@Override
	public void execute(Event event, String[] argv) {
		for(IVoiceChannel channel : event.getClient().getConnectedVoiceChannels()){
			channel.leave();
		}
	}

}
