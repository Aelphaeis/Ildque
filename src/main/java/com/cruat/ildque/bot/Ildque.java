package com.cruat.ildque.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.config.BotToken;
import com.cruat.ildque.config.Settings;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Ildque implements AutoCloseable{
	
	IDiscordClient client;

	public static final Logger logger = LogManager.getLogger();

	public Ildque() {
		ClientBuilder builder = new ClientBuilder();
		builder.withToken(Settings.LOGIN_TOKEN.value(BotToken.class).getValue());
		client = builder.build();
		client.login();
	}

	@Override
	public void close() {
		client.logout();
	}
}
