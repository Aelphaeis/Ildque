package com.cruat.ildque.bot;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cruat.ildque.config.Settings;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Ildque implements AutoCloseable{
	private static final Logger logger = LogManager.getLogger();
	
	final List<BotCommand> commands = new ArrayList<>();
	IDiscordClient client;
	
	public Ildque() {
		ClientBuilder builder = new ClientBuilder();
		String token = Settings.LOGIN_TOKEN.value(String.class);
		logger.info("Starting bot with token {}", token);
		builder.withToken(token);
		client = builder.build();
		client.login();
	}
	
	@Override
	public void close() {
		client.logout();
	}
}
