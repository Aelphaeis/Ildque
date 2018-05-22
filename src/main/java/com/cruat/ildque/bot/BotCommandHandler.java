package com.cruat.ildque.bot;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.bot.commands.Command;
import com.cruat.ildque.bot.exceptions.IldqueException;
import com.cruat.ildque.bot.utilities.DiscordHelper;
import com.cruat.ildque.bot.utilities.Reflector;
import com.cruat.ildque.bot.utilities.Strings;
import com.cruat.ildque.config.Configuration;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class BotCommandHandler implements IListener<MessageReceivedEvent> {

	private static final Logger logger = LogManager.getLogger();
	final List<BotCommand> commands = new ArrayList<>();
	final Ildque context;

	public BotCommandHandler(Ildque context) {
		this.context = context;
		ClassLoader loader = Command.class.getClassLoader();
		Package pk = Command.class.getPackage();
		for (Class<?> cls : Reflector.getClassesForPackage(pk, loader)) {
			if (BotCommand.class.isAssignableFrom(cls)) {
				registerCommand(cls);
			}
		}
	}

	@Override
	public void handle(MessageReceivedEvent event) {
		String content = event.getMessage().getContent();
		String prefix =  Configuration.load().getPrefix();
		if(!content.startsWith(prefix + " ")) {
			logger.trace("Inappropriate prefix, disregarding message");
			return;
		}
		
		String cmdText = content.substring(prefix.length());
		String[] argv = Strings.translateCommandline(cmdText);
		

		processCommand(event, argv);
	}
	
	public void processCommand(MessageReceivedEvent event, String[] argv) {

		try {
			if (argv.length < 1) {
				String err = "command prefix found but no command";
				throw new IldqueException(err);
			}

			for (BotCommand command : commands) {
				if (command.getName().equalsIgnoreCase(argv[0])) {
					command.execute(event, argv);
				}
			}
		} catch (IldqueException e) {
			DiscordHelper.sendMessage(event, e.getMessage());
		}

	}

	private void registerCommand(Class<?> cmdClass) {
		try {
			if(Modifier.isAbstract(cmdClass.getModifiers())) {
				return;
			}
			
			Constructor<?> ctor = cmdClass.getConstructor();
			BotCommand command = (BotCommand) ctor.newInstance();
			command.setContext(context);
			commands.add(command);
		} catch (Exception e) {
			logger.error("Unable to register command", e);
		}
	}

	public List<BotCommand> getCommands() {
		return Collections.unmodifiableList(commands);
	}
}
