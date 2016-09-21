package com.crusnikatelier.ildque.driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.Bot;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;

public class Program {
	private static final Logger logger = LoggerFactory.getLogger(Program.class);
	public static void main(String[] args) throws Throwable {
		
		Bot bot = new Bot(args);
		bot.run();
	}
}
