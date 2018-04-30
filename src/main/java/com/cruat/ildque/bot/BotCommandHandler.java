package com.cruat.ildque.bot;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.bot.commands.AbstractCommand;
import com.cruat.ildque.bot.utilities.Reflector;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class BotCommandHandler implements IListener<MessageReceivedEvent> {

	private static final Logger logger = LogManager.getLogger();
	final List<BotCommand> commands = new ArrayList<>();

	public BotCommandHandler() {
		Package pk = AbstractCommand.class.getPackage();

		for (Class<?> cls : Reflector.getClassesForPackage(pk)) {
			if (BotCommand.class.isAssignableFrom(cls)) {
				registerCommand(cls);
			}
		}
	}

	@Override
	public void handle(MessageReceivedEvent event) {
		String content = event.getMessage().getContent();
	}

	private void registerCommand(Class<?> cmdClass) {
		try {
			if(Modifier.isAbstract(cmdClass.getModifiers())) {
				return;
			}
			
			Constructor<?> ctor = cmdClass.getConstructor();
			commands.add((BotCommand) ctor.newInstance());
		} catch (Exception e) {
			logger.error("Unable to register command", e);
		}
	}
}
