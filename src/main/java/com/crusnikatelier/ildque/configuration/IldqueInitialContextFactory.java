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
			String key = set.getKey().toString();
			String value = set.getValue().toString();
			
			String msg = "Initializing context with environment setting {%s : %s}";
			logger.info(String.format(msg, key, value));
			defaultEnvironment.put(key, value);
		}

		Enumeration<?> keys = defaultEnvironment.keys();
		
		logger.trace("Populating initial context");
		while(keys.hasMoreElements()){
			ProcessEnvironmentVariable(defaultEnvironment, init, keys.nextElement());
		}
		return init;
	}
	
	protected static void ProcessEnvironmentVariable(Hashtable<String, String> env, Context ctxt, Object key) 
			throws NamingException{
		
		//Check to ensure all the data checks out
		String keyValue = key.toString();
		Object value = env.get(key);
		if(value == null){
			return;
		}
		
		Settings [] settings = BotConfiguration.Settings.values();
		String settingValue = value.toString();
		
		for(Settings setting : settings){
			keyValue = keyValue.replace(".", "/");
			if(setting.getShortName().equals(keyValue)){
				ctxt.bind(setting.getValue(), settingValue);
			}
		}
	}
}
