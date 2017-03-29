package com.crusnikatelier.ildque.configuration;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotConfiguration {
	private static Logger logger = LoggerFactory.getLogger(BotConfiguration.class);
	Context ctxt;
	public BotConfiguration(){ }
	
	public BotConfiguration(Hashtable<?, ?> environment){
		this();
		this.ctxt = createInitialContext(environment);
	}
	
	private InitialContext createInitialContext(Hashtable<?, ?> env){
		try{
			return new InitialContext(env);
		}
		catch(NamingException e){
			String err ="Unable to initialize context";
			throw new RuntimeException(err, e);
		}
	}
	
	public String value(Settings setting){
		try {
			return String.valueOf(ctxt.lookup(setting.getValue()));
		}
		catch (NamingException e) {
			String msg = "Unable to retrieve setting";
			logger.error(msg, e);
			throw new IllegalStateException(msg, e);
		}
	}
	
	public static enum Settings {
		PREFIX("configuration/prefix"),
		TOKEN("configuration/token"),
		LOG4J2("configuration/log4j2");
		
		protected static final String SETTING_PREFIX = "java:comp/env/ildque/";
		private String shortName;
		private String value;
		
		
		Settings(String value){
			setShortName(value);
			setValue(SETTING_PREFIX + value);
		}

		public String getValue() {
			return value;
		}

		protected void setValue(String value) {
			this.value = value;
		}

		public String getShortName() {
			return shortName;
		}

		protected void setShortName(String shortName) {
			this.shortName = shortName;
		}
		
	}
}
