package com.crusnikatelier.ildque.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import jmo.util.naming.InMemoryContext;

public class IldqueInitialContext extends InMemoryContext{
	
	Map<String, Object> bindings;
	
	public IldqueInitialContext(){
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
