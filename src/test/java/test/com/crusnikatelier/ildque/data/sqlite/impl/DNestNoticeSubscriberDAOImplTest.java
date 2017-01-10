package test.com.crusnikatelier.ildque.data.sqlite.impl;

import static org.junit.Assert.*;


import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sqlite.JDBC;

import com.crusnikatelier.ildque.data.DataAccessFactory;
import com.crusnikatelier.ildque.data.DatabaseType;
import com.crusnikatelier.ildque.data.daos.DNestNoticeSubscriberDAO;
import com.crusnikatelier.ildque.data.entities.DNestNoticeSubscriber;
import com.crusnikatelier.ildque.data.entities.User;
import com.crusnikatelier.ildque.data.sqlite.SQLiteDialect;

public class DNestNoticeSubscriberDAOImplTest {
	
	public static final String PKG_LOCATION = "src/test/java/test/com/crusnikatelier/ildque/data/sqlite/impl/";
	public static final String DB_FILENAME = "DNestNoticeSubscriberDAOImplTest.db";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Get database connection String
		String dbloc = PKG_LOCATION + DB_FILENAME;
		String connStringFormat = "jdbc:sqlite:%s";
		String connString = String.format(connStringFormat, dbloc);
		
		
		Configuration cfg = new Configuration();
		cfg.setProperty("hibernate.connection.url", connString);
		cfg.setProperty("hibernate.dialect", SQLiteDialect.class.getName());
		cfg.setProperty("connection.autocommit", "false");
		cfg.setProperty("hibernate.connection.driver_class", JDBC.class.getName());
		cfg.addAnnotatedClass(User.class);
		cfg.addAnnotatedClass(DNestNoticeSubscriber.class);
		
		DataAccessFactory.getInstance().overrideSessionFactory(cfg.buildSessionFactory());
	}

	@Test
	public void findByUserDiscordIdTest() {
		String discordId = "90879795365179392";
		DataAccessFactory factory = DataAccessFactory.getInstance();
		DNestNoticeSubscriberDAO dNestSubDAO = null;
		
		try(Session session = factory.getSession()){
			dNestSubDAO = factory.getDAO(DNestNoticeSubscriberDAO.class, DatabaseType.SQLITE, session);
			
			DNestNoticeSubscriber subscription;
			//Find user that exists
			subscription = dNestSubDAO.findByUserDiscordId(discordId);
			User user = subscription.getSubscriber();
			
			assertEquals(1, user.getId());
			assertEquals(discordId, user.getDiscordId());
			
			//Find user that doesn't exist
			subscription = dNestSubDAO.findByUserDiscordId("1111111");
			assertEquals(null, subscription);
		}
	}

}
