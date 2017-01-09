package com.crusnikatelier.ildque.configuration;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IldqueInitialContext implements Context{
	private static final Logger logger = LoggerFactory.getLogger(IldqueInitialContext.class);
	Map<String, Object> bindings;
	
	public IldqueInitialContext(){
		logger.trace("Initializing Ildque's initial context");
		bindings = new HashMap<String, Object>();
	}

	@Override
	public Object lookup(Name name) throws NamingException {
		return lookup(name.toString());
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
	public void bind(Name name, Object obj) throws NamingException {
		bindings.put(name.toString(), obj);
	}

	@Override
	public void rebind(Name name, Object obj) throws NamingException {
		bindings.put(name.toString(), obj);
	}
	
	@Override
	public void rebind(String name, Object obj) throws NamingException {
		bind(name, obj);
	}

	@Override
	public void unbind(Name name) throws NamingException {
		unbind(name.toString());
	}
	
	@Override
	public void unbind(String name) throws NamingException {
		bindings.remove(name);
	}

	@Override
	public void rename(Name oldName, Name newName) throws NamingException {
		rename(oldName, newName);
	}
	
	@Override
	public void rename(String oldName, String newName) throws NamingException {
		Object obj = bindings.get(oldName);
		bindings.remove(oldName);
		bindings.put(newName, obj);
	}

	@Override
	public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public void destroySubcontext(Name name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public void destroySubcontext(String name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public Context createSubcontext(Name name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public Context createSubcontext(String name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public Object lookupLink(Name name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public Object lookupLink(String name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public NameParser getNameParser(Name name) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public NameParser getNameParser(String name) throws NamingException {
		return new NameParser() {
			@Override
			public Name parse(String nom) throws NamingException {
				DefaultName n = new DefaultName();
				n.setValue(nom);
				return n;
			}
		};
	}

	@Override
	public Name composeName(Name name, Name prefix) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public String composeName(String name, String prefix) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public Object addToEnvironment(String propName, Object propVal) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public Object removeFromEnvironment(String propName) throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public Hashtable<?, ?> getEnvironment() throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}

	@Override
	public void close() throws NamingException {

	}

	@Override
	public String getNameInNamespace() throws NamingException {
		throw new UnsupportedOperationException("Operation not implemented yet.");
	}
}
