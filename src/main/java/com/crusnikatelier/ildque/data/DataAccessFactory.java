package com.crusnikatelier.ildque.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DataAccessFactory {
	private static final Logger logger = LoggerFactory.getLogger(DataAccessFactory.class);
	private static final DataAccessFactory instance = new DataAccessFactory();
	
	public static final DataAccessFactory getInstance(){ return instance; }
	
	private DataAccessFactory(){
		logger.info("Initializing DataAccessFactory");
		
	}
	
}
