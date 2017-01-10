package com.crusnikatelier.ildque.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crusnikatelier.utilities.MarshalHelper;

public class EntityBase {
	
	private static final Logger logger = LoggerFactory.getLogger(EntityBase.class);
	
	@Override
	public String toString(){
		try{
			return MarshalHelper.marshallJson(this);
		}
		catch(Exception e){
			logger.warn("Unable to marshal Object", e);
			return super.toString();
		}
	}
}
