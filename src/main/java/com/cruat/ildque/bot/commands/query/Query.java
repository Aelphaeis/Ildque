package com.cruat.ildque.bot.commands.query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import com.cruat.ildque.bot.commands.Command;
import com.cruat.ildque.bot.exceptions.CommandException;
import com.cruat.ildque.bot.utilities.DiscordHelper;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Query extends Command {
	private static final Logger logger = LogManager.getLogger();
	
	SessionFactory factory;
	public Query() {
		factory = createSessionFactory();
	}

	@Override
	public void execute(MessageReceivedEvent e, String[] argv) throws CommandException {
		DiscordHelper.sendMessage(e, "hello world");
		//TODO implement me
	}
	
	public SessionFactory createSessionFactory() {
		try{
			Configuration conf = new Configuration();
			conf.addPackage(Command.class.getPackage().getName());
			
			conf.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
			conf.setProperty(AvailableSettings.JPA_JDBC_URL, "jdbc:h2:mem:queryable");
			conf.setProperty(AvailableSettings.JPA_JDBC_DRIVER, "org.h2.Driver");
			conf.setProperty(AvailableSettings.JPA_JDBC_USER, "querier");
			conf.setProperty(AvailableSettings.JPA_JDBC_PASSWORD, "");
			conf.setProperty(AvailableSettings.SHOW_SQL, "true");
			
			conf.addAnnotatedClass(Server.class);
			
			logger.info("Creating sessionFactory");
			long start = System.currentTimeMillis();
			
			factory = conf.buildSessionFactory();
			
			long end = System.currentTimeMillis();
			long duration = end - start;
			String msg = "sessionFactory successful created in {} millseconds";
			logger.info(msg, duration);
			
			return factory;
		}
		catch(Exception e){
			String msg = "Unable to instantisate hibernate";
			logger.error(msg, e);
			throw new IllegalStateException(msg);
		}
	}
}
