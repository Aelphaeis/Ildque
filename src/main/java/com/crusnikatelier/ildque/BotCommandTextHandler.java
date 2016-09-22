package com.crusnikatelier.ildque;

import java.util.ArrayList;
import java.util.List;

import com.crusnikatelier.ildque.commands.TerminateCommand;
import com.crusnikatelier.utilities.StringHelper;

import  sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

public class BotCommandTextHandler implements IListener<MessageReceivedEvent> {

	Bot bot;
	List<BotCommand> commands;
	
	public BotCommandTextHandler(Bot bot) {
		this.bot = bot;
		commands = new ArrayList<BotCommand>();
		commands.add(new TerminateCommand(bot));
	}
	
	@Override
	public void handle(MessageReceivedEvent event) {
		String prefix = bot.getConfiguration().getCommandPrefix();
		String content = event.getMessage().getContent();
		
		if(!content.startsWith(prefix)){
			return;
		}

		String cmdText = content.substring(prefix.length());
		String[] argv = StringHelper.translateCommandline(cmdText);
	
		if(argv.length < 1){
			throw new IllegalStateException("argv must have at least one element");
		}
		
		for(BotCommand command : commands){
			if(command.getName().equals(argv[0])){
				command.execute(argv);
			}
		}
	}
}
