package com.crusnikatelier.ildque.configuration;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public class IldqueInitialContextFactory implements InitialContextFactory {

	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		IldqueInitialContext init = new IldqueInitialContext();
		
		//Stub in some default configuration settings
		init.bind(BotConfiguration.Settings.PREFIX.getValue(), "Ildque ");
		return init;
	}
}
