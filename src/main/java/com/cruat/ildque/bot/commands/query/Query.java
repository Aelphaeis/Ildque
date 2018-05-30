package com.cruat.ildque.bot.commands.query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cruat.ildque.bot.commands.Command;
import com.cruat.ildque.bot.exceptions.CommandException;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class Query extends Command {
	private static final Logger logger = LogManager.getLogger();

	
	public Query() {

		
	}
	
	@Override
	public void execute(MessageReceivedEvent e, String[] argv) throws CommandException {
		
	}
	
	public SessionFactory createSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		try{
			logger.info("Creating sessionFactory");
			long start = System.currentTimeMillis();
			
			SessionFactory factory = new MetadataSources ( registry )
					.buildMetadata()
					.buildSessionFactory();
			
			long end = System.currentTimeMillis();
			long duration = end - start;
			String msg = "sessionFactory successful created in {} millseconds";
			logger.info(msg, duration);
			
			return factory;
		}
		catch(Exception e){
			String msg = "Unable to instantisate hibernate";
			logger.error(msg, e);
			StandardServiceRegistryBuilder.destroy(registry);
			throw new IllegalStateException(msg);
		}
	}
}
