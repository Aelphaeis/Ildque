package com.crusnikatelier.ildque.configuration;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.ildque.configuration.BotConfiguration.Settings;

public class IldqueInitialContextFactory implements InitialContextFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(IldqueInitialContextFactory.class);
	private Hashtable<String, String> defaultEnvironment = null;
	
	public static final String PREFIX = "il ";

	
	public IldqueInitialContextFactory() {
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Settings.PREFIX.getShortName(), PREFIX);
		defaultEnvironment = environment;
	}

	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		logger.trace("Creating Initial Context");
		IldqueInitialContext init = new IldqueInitialContext();
		
		for(Entry<?, ?> set : environment.entrySet()){
			defaultEnvironment.put(set.getKey().toString(), set.getValue().toString());
		}

		Enumeration<?> keys = defaultEnvironment.keys();
		Settings [] settings = BotConfiguration.Settings.values();
	
		logger.trace("Populating initial context");
		while(keys.hasMoreElements()){
			Object key = keys.nextElement();
			Object value = defaultEnvironment.get(key);
			if(value == null){
				continue;
			}
			String settingValue = value.toString();
			String keyValue = key.toString();
			
			for(Settings setting : settings){
				keyValue = keyValue.replace(".", "/");
				if(setting.getShortName().equals(keyValue)){
					init.bind(setting.getValue(), settingValue);
				}
			}
		}
		//Stub in some default configuration settings
		return init;
	}
}
