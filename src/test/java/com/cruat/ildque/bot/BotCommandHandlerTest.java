package com.cruat.ildque.bot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cruat.ildque.bot.commands.Help;

public class BotCommandHandlerTest {

	BotCommandHandler handler;


	@Before
	public void setup() {
		handler = new BotCommandHandler();
		
	}
	
	@Test
	public void test() {
		assertTrue(handler.commands
			.stream()
			.anyMatch(p-> p.getClass().equals(Help.class)));
	}

}
