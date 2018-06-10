package com.cruat.ildque.cmd.query;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.cruat.ildque.BotCommandHandler;
import com.cruat.ildque.Ildque;
import com.cruat.ildque.cfg.Configuration;
import com.cruat.ildque.cmd.query.Query;
import com.cruat.ildque.exceptions.CommandException;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import test.util.MessageReceivedEventBuilder;
import test.util.Stubs;

public class QueryTest {
	
	MessageReceivedEvent e;
	String [] argv;
	Query cmd;

	@Before
	public void setup() {
		cmd = new Query();
		Configuration c = new Configuration();
		c.setPrefix("ils");
		
		Ildque bot = mock(Ildque.class);
		IDiscordClient client = mock(IDiscordClient.class);
		
		
		when(client.getGuilds()).thenReturn(new ArrayList<>());
		
		when(bot.getConfiguration()).thenReturn(c);
		when(bot.getClient()).thenReturn(client);
		cmd.setContext(bot);
		
		
	}
	
	@Test
	public void execute__() throws CommandException {
		MessageReceivedEventBuilder builder = new MessageReceivedEventBuilder();
		builder.onSendMessage(MessageReceivedEventBuilder.SEND_MSG_LOGGER);
		e = builder.content("ils query from Server").build();
		String content = e.getMessage().getContent();
		
		argv = BotCommandHandler.resolveArgv(content, Stubs.DEFAULT_PREFIX);
		
		cmd.execute(e, argv);
	}
}
