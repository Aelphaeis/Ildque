package com.crusnikatelier.ildque.configuration;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IldqueInitialContextFactory implements InitialContextFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(IldqueInitialContextFactory.class);

	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		
		logger.trace("Creating Initial Context");
		IldqueInitialContext init = new IldqueInitialContext();
		
		
		logger.trace("Populating initial context");
		//Stub in some default configuration settings
		init.bind(BotConfiguration.Settings.PREFIX.getValue().toString(), "Ildque ");
		return init;
	}
}
