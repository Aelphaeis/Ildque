package com.cruat.ildque.bot.cmd;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.cruat.ildque.bot.BotCommandHandler;
import com.cruat.ildque.bot.cmd.Site;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import test.util.MessageReceivedEventBuilder;
import test.util.Stubs;

@RunWith(MockitoJUnitRunner.class)
public class SiteTest {

	MessageReceivedEvent e;
	
	String argv[];
	Site cmd;
	
	@Before
	public void setup() {
		//build message event
		MessageReceivedEventBuilder builder = new MessageReceivedEventBuilder();
		e = builder.content("ils site").build();
		
		//initialize site
		cmd = new Site();
		
		//get message event arguments
		String content = e.getMessage().getContent();
		argv = BotCommandHandler.resolveArgv(content, Stubs.DEFAULT_PREFIX);
	}
	
	@Test
	public void execute_validInput_messageReturned() {
		String expected = Site.MESSAGE;
		cmd.execute(e, argv);
		verify(e.getMessage().getChannel()).sendMessage(expected);
	}

}
