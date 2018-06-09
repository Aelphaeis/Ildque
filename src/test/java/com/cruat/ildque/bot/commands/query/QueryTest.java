package com.cruat.ildque.bot.commands.query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.cruat.ildque.bot.BotCommandHandler;
import com.cruat.ildque.bot.exceptions.CommandException;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import test.util.MessageReceivedEventBuilder;
import test.util.Stubs;

public class QueryTest {
	
	private static final Logger logger = LogManager.getLogger();
	MessageReceivedEvent e;
	Query cmd;
	String [] argv;

	@Before
	public void setup() {
		
	}
	
	@Test
	public void execute__() throws CommandException {
		MessageReceivedEventBuilder builder = new MessageReceivedEventBuilder();
		builder.onSendMessage(MessageReceivedEventBuilder.SEND_MSG_LOGGER);
		e = builder.content("ils query").build();
		cmd = new Query();
		String content = e.getMessage().getContent();
		argv = BotCommandHandler.resolveArgv(content, Stubs.DEFAULT_PREFIX);
		
		cmd.execute(e, argv);
		
		
	}
}
