package com.crusnikatelier.ildque.data;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteDataSource;

import com.crusnikatelier.ildque.configuration.Settings;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

public final class DataAccessFactory {
	private static final Logger logger = LoggerFactory.getLogger(DataAccessFactory.class);
	private static final DataAccessFactory instance = new DataAccessFactory();
	
	public static final DataAccessFactory getInstance(){ return instance; }
	public static final String CHANGELOG_PATH = "changelog/changelog.xml";
	
	private SessionFactory sessionFactory;
	
	private DataAccessFactory(){
		logger.debug("Initializing DataAccessFactory");
		runLiquibase();
	}
	
	public Session getSession(){
		return sessionFactory.openSession();	
	}
	
	
	void runLiquibase(){
		logger.debug("Running  Liquibase");
		long start = System.currentTimeMillis();
		try {
			DataSource ds = (DataSource) getSqlite3DataSource();
			DatabaseFactory dbFactory = DatabaseFactory.getInstance();
			
			JdbcConnection connection = new JdbcConnection(ds.getConnection());
			Database db = dbFactory.findCorrectDatabaseImplementation(connection);
			ResourceAccessor accessor = new ClassLoaderResourceAccessor();
			Liquibase liquibase = new Liquibase(CHANGELOG_PATH, accessor, db);
			liquibase.update(new Contexts(), new LabelExpression());
		} catch (SQLException | LiquibaseException e) {
			String msg = "Unable to run Liquibase";
			logger.error(msg, e);
			//Application should not be run if this fails
			throw new IllegalStateException(msg);
		}
		long end = System.currentTimeMillis();
		long duration = end - start;
		String msg = "Liquibase has successful ran in {} millseconds";
		logger.debug(msg, duration);
	}
	
	DataSource getSqlite3DataSource(){
		try{
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e){
			logger.error(e.getMessage(), e);
		}
		
		SQLiteDataSource dSource = new SQLiteDataSource();
		dSource.setUrl(String.valueOf(Settings.DB_CONN_STRING.value()));
		return dSource;
	}
	
	SessionFactory createSessionFactory(){
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		try{
			logger.info("Creating sessionFactory");
			long start = System.currentTimeMillis();
			
			MetadataSources sources = new MetadataSources(registry);
			SessionFactory factory = sources
					.buildMetadata()
					.buildSessionFactory();
			
			long end = System.currentTimeMillis();
			long duration = end - start;
			String msg = "sessionFactory successful created in {} millseconds";
			logger.info(msg, duration);
			
			return factory;
		}
		catch(Exception e){
			String msg = "Unable to instantisate hibernate";
			logger.error(msg, e);
			StandardServiceRegistryBuilder.destroy(registry);
			throw new IllegalStateException(msg);
		}
		
	}
}
