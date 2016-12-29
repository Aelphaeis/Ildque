package com.crusnikatelier.ildque.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmo.util.naming.InMemoryContext;

public class IldqueInitialContext extends InMemoryContext{
	private static final Logger logger = LoggerFactory.getLogger(IldqueInitialContext.class);
	Map<String, Object> bindings;
	
	public IldqueInitialContext(){
		logger.trace("Initializing Ildque's initial context");
		bindings = new HashMap<String, Object>();
	}
	
	@Override
	public Object lookup(String name) throws NamingException {
		return bindings.get(name);
	}
	
	@Override
	public void bind(String name, Object obj) throws NamingException {
		bindings.put(name, obj);
	}
	
	@Override
	public void rebind(String name, Object obj) throws NamingException {
		bind(name, obj);
	}

	@Override
	public void unbind(String name) throws NamingException {
		bindings.remove(name);
	}
	
	@Override
	public void rename(String oldName, String newName) throws NamingException {
		Object obj = bindings.get(oldName);
		bindings.remove(oldName);
		bindings.put(newName, obj);
	}
}
