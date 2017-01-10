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
import com.crusnikatelier.ildque.data.daos.DNestNoticeSubscriberDAO;
import com.crusnikatelier.ildque.data.daos.DNestNoticeTrackerDAO;
import com.crusnikatelier.ildque.data.daos.UserDAO;
import com.crusnikatelier.ildque.data.sqlite.impl.DNestNoticeSubscriberDAOImpl;
import com.crusnikatelier.ildque.data.sqlite.impl.DNestNoticeTrackerDAOImpl;
import com.crusnikatelier.ildque.data.sqlite.impl.UserDAOImpl;

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
	private SessionFactory override;
	
	private DataAccessFactory(){
		init();
	}
	
	public Session getSession(){
		return override == null? sessionFactory.openSession() : override.openSession();	
	}
	
	public Session getReadonlySession(){
		Session session = getSession();
		session.setDefaultReadOnly(true);
		return session;
	}
	
	public <T extends DataAccessObject<?>> T getDAO(Class<T> clazz, DatabaseType type){
		return getDAO(clazz, type, getSession());
	}	
	
	@SuppressWarnings("unchecked")
	public <T extends DataAccessObject<?>> T getDAO(Class<T> clazz, DatabaseType type, Session session){
		switch(type){
			case SQLITE:
				if(clazz.isAssignableFrom(UserDAO.class)){
					UserDAO dao = new UserDAOImpl(session);
					return (T) dao;
				}
				if(clazz.isAssignableFrom(DNestNoticeSubscriberDAO.class)){
					DNestNoticeSubscriberDAO dao = new DNestNoticeSubscriberDAOImpl(session);
					return (T) dao;
				}
				if(clazz.isAssignableFrom(DNestNoticeTrackerDAO.class)){
					DNestNoticeTrackerDAO dao = new DNestNoticeTrackerDAOImpl(session);
					return (T) dao;
				}
				break;
			default : 
				String msg = "Invalid DatabaseType specified";
				throw new IllegalArgumentException(msg);
		}
		String msg = "Unable to find specified DAO";
		throw new IllegalArgumentException(msg);
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
			throw new IllegalStateException(msg, e);
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
			throw new IllegalStateException(msg, e);
		}
	}
	
	public void init(){
		logger.debug("Initializing DataAccessFactory");
		runLiquibase();
		
		sessionFactory = createSessionFactory();
	}
	
	/**
	 * This is a very dangerous method, do not use this if you don't understand
	 * what you are doing. 
	 * 
	 * Use this method to override the applications session factory.
	 * This will change how the entire application gets its Data Access Objects
	 * @param sessionFactory
	 */
	public void overrideSessionFactory(SessionFactory sessionFactory) {
		this.override = sessionFactory;
	}
	
	public void removeOverride(){
		this.override = null;
	}
	
}
