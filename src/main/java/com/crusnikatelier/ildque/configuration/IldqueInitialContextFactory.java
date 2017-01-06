package com.crusnikatelier.ildque.configuration;

import java.io.File;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteDataSource;

public class IldqueInitialContextFactory implements InitialContextFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(IldqueInitialContextFactory.class);

	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		
		logger.trace("Creating Initial Context");
		IldqueInitialContext init = new IldqueInitialContext();
		
		
		logger.trace("Populating initial context");
		//Stub in some default configuration settings
		init.bind(Settings.PREFIX.getName(), "Ildque ");
		init.bind(Settings.DB_CONN_STRING.getName(), getSqlite3DbConnectionString());
		init.bind(Settings.DB_DATASOURCE.getName(), createSqlite3DbDatasource());
		return init;
	}
	
	public String getSqlite3DbConnectionString(){
		String format = "jdbc:sqlite3:%s";
		String path = new File("db/ildque.db").getAbsolutePath();
		return String.format(format, path);
	}
	
	public DataSource createSqlite3DbDatasource(){
		try{
			Class.forName("org.sqlite.JDBC");
			SQLiteDataSource dataSource = new SQLiteDataSource();
			dataSource.setUrl(getSqlite3DbConnectionString());
			return dataSource;
		}
		catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	
}
