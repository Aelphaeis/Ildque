package com.crusnikatelier.ildque.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Settings {
	PREFIX("java:comp/env/ildque/configuration/prefix"),
	DB_CONN_STRING("java:comp/env/ildque/configuration/connectionString"),
	DB_DATASOURCE("java:comp/env/ildque/configuration/datasource");
	
	private static Logger logger = LoggerFactory.getLogger(Settings.class);
	

	String name;
	
	private Settings(String name){
		this.name = name;
	}
	
	public Object value() {
		try {
			Context initialContext = new InitialContext();
			return initialContext.lookup(name);
		}
		catch (NamingException e) {
			String msg = "Unable to retrieve setting";
			logger.error(msg, e);
			throw new IllegalStateException(msg, e);
		}
	}
	
	public String getName(){
		return name;
	}
}
