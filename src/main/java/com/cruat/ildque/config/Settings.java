package com.cruat.ildque.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public enum Settings {
	LOGIN_TOKEN("java:comp/env/ildque/token");
	
	private static final Logger logger = LogManager.getLogger();
	
	public static Object value(Settings setting){
		return value(setting.getName());
	}
	
	static Object value(String name) {
		try {
			return new InitialContext().lookup(name);
		}
		catch(NamingException e) {
			logger.warn("Unable to retrieve setting", e);
			return null;
		}
	}
	
	static boolean check(String name) {
		try {
			return new InitialContext().lookup(name) == null;
		}
		catch(NamingException e) {
			return false;
		}
	}
	
	
	Settings(String name){
		this(name, false);
	}
	
	Settings(String name, boolean nullible){
		this.name = name;
		this.nullible = nullible;
		if(!this.nullible && !check(name)) {
			throw new IllegalStateException("Bad settings");
		}
	}
	
	private final String name;
	private final boolean nullible;

	public String getName() {
		return name;
	}

	public boolean isNullible() {
		return nullible;
	}
	
	public <T> T getValue(Class<T> cls){
		return cls.cast(getValue());
	}
	
	public Object getValue(){
		return value(this);
	}
}
