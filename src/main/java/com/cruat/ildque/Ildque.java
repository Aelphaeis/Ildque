package com.cruat.ildque;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.cfg.Configuration;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Ildque implements AutoCloseable {
	private static final Logger logger = LogManager.getLogger();
	
	final IDiscordClient client;
	final BotCommandHandler handler;
	final Configuration configuration;
	
	public Ildque(Configuration conf) {
		configuration = conf;
		ClientBuilder builder = new ClientBuilder();
		String token = conf.getToken();
		logger.info("Starting bot with token {}", token);
		builder.withToken(token);
		
		
		client = builder.build();
		handler = new BotCommandHandler(this);
		client.getDispatcher().registerListener(handler);
		client.login();
		
	}
	
	@Override
	public void close() {
		client.logout();
	}

	public IDiscordClient getClient() {
		return client;
	}

	public BotCommandHandler getHandler() {
		return handler;
	}

	public Configuration getConfiguration() {
		return configuration;
	}
}
