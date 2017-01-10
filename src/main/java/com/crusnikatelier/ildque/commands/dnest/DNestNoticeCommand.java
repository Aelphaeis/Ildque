package com.crusnikatelier.ildque.commands.dnest;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.BotCommand;
import com.crusnikatelier.ildque.data.DataAccessFactory;
import com.crusnikatelier.ildque.data.DatabaseType;
import com.crusnikatelier.ildque.data.daos.DNestNoticeSubscriberDAO;
import com.crusnikatelier.ildque.data.daos.UserDAO;
import com.crusnikatelier.ildque.data.entities.DNestNoticeSubscriber;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class DNestNoticeCommand implements BotCommand{

	Logger logger = LoggerFactory.getLogger(DNestNoticeCommand.class);
	
	@Override
	public String getName() {
		return "dnestnotice";
	}

	@Override
	public Options getOptions() {
		Options options = new Options();
		options.addOption(new Option("s", "subscribe", false, "Subscribe to dragon nest notices"));
		options.addOption(new Option("u", "unsubscribe", false, "Unsubscribe to dragon nest notces"));
		return options;
	}

	@Override
	public void execute(Event event, String[] argv) {
		MessageReceivedEvent msgEvent = (MessageReceivedEvent)event;
		IMessage msg = msgEvent.getMessage();
		IUser user = msg.getAuthor();
	}
	
	String processInput(IUser user,String [] argv){
		try{
			CommandLineParser parser = new DefaultParser();
			CommandLine line = parser.parse(getOptions(), argv);
			
			boolean unsubscribe = line.hasOption('u');
			boolean subscribe = line.hasOption('s');

			if(subscribe && unsubscribe){
				String msg = "cannot specify both unsubscribe and subscribe";
				throw new IllegalArgumentException(msg);
			}
			
			String response = "";
			
			if(subscribe){
				response = handleSubscribe(user);
			}
			else if(unsubscribe){
				response = handleUnsubscribe(user);
			}
			else {
				response = handleCheck(user);
			}
			
			return response;
			
		}
		catch(ParseException e){
			String err = "Unable to parse arguments";
			logger.error(err, e);
			return err;
		}
	}
	
	String handleSubscribe(IUser user){
		DataAccessFactory factory = DataAccessFactory.getInstance();
		DNestNoticeSubscriberDAO subscribers = null;
		String response = null;
		
		try (Session session = factory.getSession()){
			//We will need these DAOS
			subscribers = factory.getDAO(DNestNoticeSubscriberDAO.class, DatabaseType.SQLITE, session);
		}
		return response;
	}
	
	String handleUnsubscribe(IUser user){
		DataAccessFactory factory = DataAccessFactory.getInstance();
		DNestNoticeSubscriberDAO subscribers = null;
		
		try (Session session = factory.getSession()){
			//We will need these DAOS
			subscribers = factory.getDAO(DNestNoticeSubscriberDAO.class, DatabaseType.SQLITE, session);
			DNestNoticeSubscriber sub = subscribers.findByUserDiscordId(user.getID());
			if(sub != null){
				subscribers.delete(sub);
				String format = "%s : Your subscription has been successfully removed";
				return String.format(format, user.mention());
			}
			else{
				String format = "%s : You are not subscribed";
				return String.format(format, user.mention());
			}	
		}
	}
	
	String handleCheck(IUser user){
		DataAccessFactory factory = DataAccessFactory.getInstance();
		DNestNoticeSubscriberDAO subscribers = null;
		
		try (Session session = factory.getSession()){
			//We will need these DAOS
			subscribers = factory.getDAO(DNestNoticeSubscriberDAO.class, DatabaseType.SQLITE, session);
			DNestNoticeSubscriber sub = subscribers.findByUserDiscordId(user.getID());
			
			String format = "%s : You are%ssubscribed";
			return String.format(format, user.mention(), sub == null ? " not " : " ");
		}
	}
}
