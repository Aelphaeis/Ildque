package com.cruat.ildque;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cruat.ildque.cfg.Configuration;
import com.cruat.ildque.cmd.Command;
import com.cruat.ildque.exceptions.CommandException;
import com.cruat.ildque.util.DiscordHelper;
import com.cruat.ildque.util.Reflector;
import com.cruat.ildque.util.Strings;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class BotCommandHandler implements IListener<MessageReceivedEvent> {

	public static String[] resolveArgv(String content, String p) {
		String cmdText = content.substring(p.length());
		return  Strings.translateCommandline(cmdText);
	}
	
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
		String prefix =  Configuration.load().getPrefix() + " ";
		if(!content.startsWith(prefix)) {
			logger.trace("Inappropriate prefix, disregarding message");
			return;
		}
		
		String[] argv =  resolveArgv(content, prefix);
		processCommand(event, argv);
	}
	
	public void processCommand(MessageReceivedEvent event, String[] argv) {

		try {
			if (argv.length < 1) {
				String err = "command prefix found but no command";
				throw new CommandException(err);
			}

			for (BotCommand command : commands) {
				if (command.getName().equalsIgnoreCase(argv[0])) {
					command.execute(event, argv);
				}
			}
		} catch (CommandException e) {
			DiscordHelper.sendMessage(event, e.getMessage());
		} catch(Exception e) {
			String err = "Unknown Exception occurred";
			logger.fatal(err, e);
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
