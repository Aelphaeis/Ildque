package com.crusnikatelier.ildque;


import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.configuration.BotConfiguration;
import com.crusnikatelier.ildque.configuration.BotConfiguration.Settings;

import sx.blah.discord.Discord4J;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RateLimitException;

public class Bot implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Bot.class);
	private BotConfiguration configuration = null;
	private IDiscordClient serviceClient;
	
	public Bot() throws DiscordException{
		this(null);
	}
	
	public Bot(Hashtable<?, ?> environment) throws DiscordException{
		configuration = new BotConfiguration(environment);
		ClientBuilder builder = new ClientBuilder();
		Discord4J.ignoreChannelWarnings.set(true);
		
		String token = configuration.value(Settings.TOKEN);
		builder.withToken(token);
		
		serviceClient = builder.build();
		
		IListener<MessageReceivedEvent> cmdHandler = new BotCommandTextHandler(this);
		serviceClient.getDispatcher().registerListener(cmdHandler);
	}
	
	public void run() {
		login();
	}
	
	
	public void login(){
		try {
			serviceClient.login();
		} 
		catch (DiscordException | RateLimitException e) {
			logger.error("Unable to login", e);
		}
	}

	public BotConfiguration getConfiguration() {
		return configuration;
	}
}
