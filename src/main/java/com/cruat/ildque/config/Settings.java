package com.cruat.ildque.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public enum Settings {
	CONNECTION_STRING("java:comp/env/jdbc/sqlite"),
	DATASOURCE("java:comp/env/datasource"),
	PROCESS_NAME("java:comp/env/process/name");
	
	private static final Logger logger = LogManager.getLogger();
	
	public static Object value(Settings setting){
		try{
			Context init = new InitialContext();
			String lookupName = setting.getName();
			return init.lookup(lookupName);
		}
		catch(NamingException e){
			String msg = "Unable to retrieve setting";
			logger.error(msg, e);
			throw new IllegalStateException(msg, e);
		}
	}
	
	Settings(String name){
		this(name, false);
	}
	
	Settings(String name, boolean nullible){
		this.name = name;
		this.nullible = nullible;
		if(!this.nullible) {
			if(value(this) == null) {
				throw new RuntimeException("configuration is bad");
			}
		}
	}
	
	private final String name;
	private final boolean nullible;

	public String getName() {
		return name;
	}

	public Object getValue(){
		return value(this);
	}

	public boolean isNullible() {
		return nullible;
	}
}
