package com.crusnikatelier.ildque;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.configuration.BotConfiguration;
import com.crusnikatelier.utilities.StringHelper;

import jmo.util.Reflector;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class BotCommandTextHandler implements IListener<MessageReceivedEvent> {
	public static final String COMMAND_PACKAGE = "com.crusnikatelier.ildque.commands";
	private static final Logger logger = LoggerFactory.getLogger(BotCommandTextHandler.class);
	private static final String COMMAND_LOG_FORMAT = "User %s Executing Command : %s";
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
		String pkgInfo = COMMAND_PACKAGE + "." + "TerminateCommand";
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
		MsgLogger.log(event);
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
				logCommandCall(event);
				String [] args = Arrays.copyOfRange(argv, 1, argv.length);
				command.execute(event, args);
			}
		}

		for (BotSpecialCommand command : specialCommands) {
			if (command.getName().equals(argv[0])) {
				logCommandCall(event);
				String [] args = Arrays.copyOfRange(argv, 1, argv.length);
				command.execute(bot, this, event, args);
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
	
	private void logCommandCall(MessageReceivedEvent evt){
		String uid = evt.getMessage().getAuthor().getID();
		String cmd = evt.getMessage().getContent();
		String msg = String.format(COMMAND_LOG_FORMAT, uid, cmd);
		logger.info(msg);
	}
	
	private static class MsgLogger{
		private final static Logger msgLogger = LoggerFactory.getLogger(MsgLogger.class);
		//Date, channel id, user id, content
		private final static String LOG_FORMAT = "[%s][%s][%s]: %s";
		public static void log(MessageReceivedEvent event){
			IMessage msg = event.getMessage();
			LocalDateTime when = msg.getCreationDate();
			String where = msg.getChannel().getID();
			String who = msg.getAuthor().getID();
			String what = msg.getContent();
			
			String log = String.format(LOG_FORMAT, when, where, who, what);
			msgLogger.info(log);
		}
	}
}
