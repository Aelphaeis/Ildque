package com.crusnikatelier.ildque;

import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

public class BotCommandTextHandler implements IListener<MessageReceivedEvent> {

	BotConfiguration config;
	
	public BotCommandTextHandler(BotConfiguration configuration) {
		setConfig(configuration);
	}
	
	@Override
	public void handle(MessageReceivedEvent event) {
		String prefix = getConfig().getCommandPrefix();
		if(!event.getMessage().getContent().startsWith(prefix)){
			return;
		}
	}

	protected BotConfiguration getConfig() {
		return config;
	}

	protected void setConfig(BotConfiguration config) {
		this.config = config;
	}
}
