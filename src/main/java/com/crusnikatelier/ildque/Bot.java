package com.crusnikatelier.ildque;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

public class Bot implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Bot.class);
	private IDiscordClient serviceClient;
	
	public Bot(String []  args) throws DiscordException{
		ClientBuilder builder = new ClientBuilder();
		if(args.length < 1){
			throw new IllegalArgumentException("No Token Specified");
		}
		builder.withToken(args[0]);
		serviceClient = builder.build();
	}
	
	public void run() {
		try {
			serviceClient.login();
		} 
		catch (DiscordException e) {
			logger.error("Unable to login", e);
		}
	}
}