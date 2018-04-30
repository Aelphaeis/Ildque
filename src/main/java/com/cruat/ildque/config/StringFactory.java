package com.cruat.ildque.config;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

import org.apache.naming.ResourceRef;

public class StringFactory implements ObjectFactory {

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
			throws Exception {
		ResourceRef reference = (ResourceRef)obj;
		return String.valueOf(reference.get("value").getContent());
	}
}
