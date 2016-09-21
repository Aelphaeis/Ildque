package com.crusnikatelier.ildque.driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;

public class Program {
	private static final Logger logger = LoggerFactory.getLogger(Program.class);
	public static void main(String[] args) throws Throwable {
		logger.info("Start Ildque");
		IDiscordClient client =  new ClientBuilder().withToken(args[0]).login();
		client.getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {

			@Override
			public void handle(MessageReceivedEvent event) {
				logger.info(event.getMessage().getContent());
			}
		});
		IUser user = client.getUserByID("195242806795698176");
	}
}
