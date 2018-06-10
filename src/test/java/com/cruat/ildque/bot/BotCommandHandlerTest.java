package com.cruat.ildque.bot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cruat.ildque.bot.cmd.Help;

@RunWith(MockitoJUnitRunner.class)
public class BotCommandHandlerTest {

	@Mock
	Ildque ildque;

	@InjectMocks
	BotCommandHandler handler;

	@Before
	public void setup() {
		handler = new BotCommandHandler(ildque);
	}

	@Test
	public void ctor_validContext_helpRegistered() {
		assertTrue(handler.commands.stream()
				.anyMatch(p -> p.getClass().equals(Help.class)));
	}
}
