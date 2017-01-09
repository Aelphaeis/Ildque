package com.crusnikatelier.ildque.configuration;

import java.io.File;
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
		init.bind(Settings.PREFIX.getName(), "Ildque ");
		init.bind(Settings.DB_CONN_STRING.getName(), getSqlite3DbConnectionString());
		init.bind(Settings.DB_DATASOURCE.getName(), null);
		
		return init;
	}
	
	public String getSqlite3DbConnectionString(){
		String format = "jdbc:sqlite:%s";
		File db = new File("db/ildque.db");
		String path = db.getAbsolutePath();
		File parent = db.getParentFile();
		parent.mkdirs();
		
		return String.format(format, path);
	}
}
