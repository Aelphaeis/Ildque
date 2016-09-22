package com.crusnikatelier.ildque.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotConfiguration {
	private static Logger logger = LoggerFactory.getLogger(BotConfiguration.class);
	
	public static String value(Settings setting){
		try {
			Context initialContext = new InitialContext();
			return String.valueOf(initialContext.lookup(setting.getValue()));
		}
		catch (NamingException e) {
			String msg = "Unable to retrieve setting";
			logger.error(msg, e);
			throw new IllegalStateException(msg, e);
		}
	}
	
	public static enum Settings {
		PREFIX("java:comp/env/ildque/configuration/prefix");
		
		private String value;
		
		Settings(String value){
			setValue(value);
		}

		public String getValue() {
			return value;
		}

		protected void setValue(String value) {
			this.value = value;
		}
	}
}
