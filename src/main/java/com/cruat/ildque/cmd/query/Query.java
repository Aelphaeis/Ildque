package com.cruat.ildque.cmd.query;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import com.cruat.ildque.Ildque;
import com.cruat.ildque.cmd.Command;
import com.cruat.ildque.exceptions.IldqueRuntimeException;
import com.cruat.ildque.util.DiscordHelper;
import com.cruat.ildque.util.Reflector;
import com.cruat.ildque.util.Serializer;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;

public class Query extends Command {
	private static final Logger logger = LogManager.getLogger();

	SessionFactory factory;
	
	public Query() {
		factory = createSessionFactory();
	}

	@Override
	public void execute(MessageReceivedEvent e, String[] argv) {
		String prefix = getContext().getConfiguration().getPrefix();
		prefix += " " + getName();
		String content = e.getMessage().getContent();
		content = content.substring(prefix.length() + 1);

		try (Session session = factory.openSession()) {
			StringBuilder builder = new StringBuilder();
			for (Object s : session.createQuery(content).list()) {
				builder.append(Serializer.serialize(s));
			}
			DiscordHelper.sendCodeMessage(e, builder.toString());
		} catch (JAXBException e1) {
			throw new IldqueRuntimeException(e1);
		}
	}


	public SessionFactory createSessionFactory() {
		try {
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

			
			Package pkg = Query.class.getPackage();
			Reflector.getClassesForPackage(pkg).stream()
				.filter(Queryable.class::isAssignableFrom)
				.forEach(conf::addAnnotatedClass);

			logger.info("Creating sessionFactory");
			long start = System.currentTimeMillis();

			factory = conf.buildSessionFactory();

			long end = System.currentTimeMillis();
			long duration = end - start;
			String msg = "sessionFactory successful created in {} millseconds";
			logger.info(msg, duration);

			return factory;
		} catch (Exception e) {
			String msg = "Unable to instantisate hibernate";
			logger.error(msg, e);
			throw new IllegalStateException(msg);
		}
	}
	
	@Override
	public void setContext(Ildque context) {
		try (Session session = factory.openSession()) {
			Transaction t = session.beginTransaction();
			for (IGuild guild : context.getClient().getGuilds()) {
				session.persist(new Server(guild));
			}
			t.commit();
		}
		super.setContext(context);
	}
}
