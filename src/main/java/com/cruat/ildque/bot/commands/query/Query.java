package com.cruat.ildque.bot.commands.query;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		stubServer();
		
	}

	@Override
	public void execute(MessageReceivedEvent e, String[] argv) throws CommandException {
		DiscordHelper.sendMessage(e, "hello world");
		//TODO implement me
	}
	
	//TODO remove me later
	public void stubServer() {
		Server s = new Server();
		s.name = "server";
		s.icon = "icon";
		s.iconUrl = "url";
		s.id = "id";
		
		try (Session session = factory.openSession()){
			Transaction t = session.beginTransaction();
			session.persist(s);
			t.commit();	
		}
	}
	
	public SessionFactory createSessionFactory() {
		try{
			Configuration conf = new Configuration();
			conf.addPackage(Command.class.getPackage().getName());
			
			conf.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
			conf.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			conf.setProperty(AvailableSettings.URL, "jdbc:h2:mem:queryable");
			conf.setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop");
			conf.setProperty(AvailableSettings.DRIVER, "org.h2.Driver");
			conf.setProperty(AvailableSettings.USER, "querier");
			conf.setProperty(AvailableSettings.AUTOCOMMIT, "true");
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
