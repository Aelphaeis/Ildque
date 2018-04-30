package com.cruat.ildque.bot;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.config.BotToken;
import com.cruat.ildque.config.Settings;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Ildque implements AutoCloseable{
	private static final Logger logger = LogManager.getLogger();
	
	final List<BotCommand> commands = new ArrayList<>();
	IDiscordClient client;
	
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
