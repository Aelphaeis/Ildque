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
	Bot bot;
	
	public BotCommandTextHandler(Bot bot) {
		this.bot = bot;
		String pkgInfo = COMMAND_PACKAGE + "." + "package-info";
		commands = new ArrayList<BotCommand>();
		try {
			Package pkg = Class.forName(pkgInfo).getPackage();
			List<Class<?>> classes = Reflector.getClassesForPackage(pkg);
			
			for(Class<?> clazz : classes){
				if(BotCommand.class.isAssignableFrom(clazz)){
					Constructor<?> ctor = clazz.getConstructor();
					Object obj = ctor.newInstance(new Object[0]);
					BotCommand cmd =  (BotCommand)obj;

					logger.info("registering command " + cmd.getName()); 
					commands.add(cmd);
				}
			}
		} 
		catch (ClassNotFoundException | NoSuchMethodException |
				SecurityException | InstantiationException |
				IllegalAccessException | IllegalArgumentException | 
				InvocationTargetException e ) {
			String msg = "Unable to load commands from " + pkgInfo;
			logger.error(msg);
			throw new IllegalStateException(msg, e);
		}
	}
	
	@Override
	public void handle(MessageReceivedEvent event) {
		String prefix = BotConfiguration.value(BotConfiguration.Settings.PREFIX);
		String content = event.getMessage().getContent();
		
		if(!content.startsWith(prefix)){
			return;
		}
		
		String cmdText = content.substring(prefix.length());
		String[] argv = StringHelper.translateCommandline(cmdText);
	
		if(argv.length < 1){
			throw new IllegalStateException("argv must have at least one element");
		}
		
		for(BotCommand command : commands){
			if(command.getName().equals(argv[0])){
				command.execute(bot, event, argv);
			}
		}
	}
}
