package com.crusnikatelier.ildque;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.configuration.BotConfiguration;
import com.crusnikatelier.utilities.StringHelper;

import jmo.util.Reflector;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

public class BotCommandTextHandler implements IListener<MessageReceivedEvent> {
	public static final String COMMAND_PACKAGE = "com.crusnikatelier.ildque.commands";
	private static final Logger logger = LoggerFactory.getLogger(BotCommandTextHandler.class);
	List<BotCommand> commands;
	List<BotSpecialCommand> specialCommands;
	Bot bot;

	public BotCommandTextHandler() {
		commands = new ArrayList<BotCommand>();
		specialCommands = new ArrayList<BotSpecialCommand>();
	}

	public BotCommandTextHandler(Bot bot) {
		this();
		this.bot = bot;
		String pkgInfo = COMMAND_PACKAGE + "." + "package-info";
		try {
			Package pkg = Class.forName(pkgInfo).getPackage();
			List<Class<?>> classes = Reflector.getClassesForPackage(pkg);

			for (Class<?> clazz : classes) {
				registerCommand(clazz);
			}
		} catch (ClassNotFoundException e) {
			String err = "Unable to find package-info";
			logger.error(err, e);
			throw new IllegalStateException(err, e);
		}
	}

	@Override
	public void handle(MessageReceivedEvent event) {
		String content = event.getMessage().getContent();

		if (!content.startsWith(getPrefix())) {
			return;
		}

		String cmdText = content.substring(getPrefix().length());
		String[] argv = StringHelper.translateCommandline(cmdText);

		if (argv.length < 1) {
			throw new IllegalStateException("argv must have at least one element");
		}

		for (BotCommand command : commands) {
			if (command.getName().equals(argv[0])) {
				command.execute(event, argv);
			}
		}

		for (BotSpecialCommand command : specialCommands) {
			if (command.getName().equals(argv[0])) {
				command.execute(bot, this, event, argv);
			}
		}
	}
	
	public String getPrefix(){
		return BotConfiguration.value(BotConfiguration.Settings.PREFIX);
	}

	public List<BotCommand> getCommands() {
		return commands;
	}

	private void registerCommand(Class<?> clazz) {
		try {
			if (BotCommand.class.isAssignableFrom(clazz)) {
				Constructor<?> ctor = clazz.getConstructor();
				Object obj = ctor.newInstance(new Object[0]);
				BotCommand cmd = (BotCommand) obj;
	
				logger.info("registering command " + cmd.getName());
				commands.add(cmd);
				
			}
			if (BotSpecialCommand.class.isAssignableFrom(clazz)) {
				Constructor<?> ctor = clazz.getConstructor();
				Object obj = ctor.newInstance(new Object[0]);
				BotSpecialCommand cmd = (BotSpecialCommand) obj;

				logger.info("registering command " + cmd.getName());
				specialCommands.add(cmd);
			} 
		} 
		catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			logger.error("Unable to register command", e);
		}
	}
}
