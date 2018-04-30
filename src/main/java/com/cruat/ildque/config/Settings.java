package com.cruat.ildque.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public enum Settings {
	LOGIN_TOKEN("java:comp/env/ildque/token");
	
	private static final Logger logger = LogManager.getLogger();
	
	public static Object value(Settings setting){
		return value(setting.nom);
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
	/**
	 * Returns true of object is not null and false if object is null or error occurred creating object.
	 * 
	 * @param name
	 * @return
	 */
	static boolean check(String name) {
		try {
			return new InitialContext().lookup(name) != null;
		}
		catch(NamingException e) {
			return false;
		}
	}
	
	
	Settings(String name){
		this(name, false);
	}
	
	Settings(String name, boolean nullible){
		this.nom = name;
		this.nullible = nullible;
		if(!this.nullible && !check(name)) {
			throw new IllegalStateException("Bad settings");
		}
	}
	
	public final String nom;
	public final boolean nullible;

	/**
	 * Get the value of the setting and cast it
	 * @param cls
	 * @return
	 */
	public <T> T value(Class<T> cls){
		return cls.cast(value());
	}
	
	
	/**
	 * Get value of the setting 
	 * @return
	 */
	public Object value(){
		return value(this);
	}
}
