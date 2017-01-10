package test.com.crusnikatelier.ildque.driver;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.crusnikatelier.ildque.data.DataAccessFactory;
import com.crusnikatelier.ildque.data.DatabaseType;
import com.crusnikatelier.ildque.data.daos.UserDAO;
import com.crusnikatelier.ildque.data.entities.User;


public class Playground {

	@Test
	public void test() {
		DataAccessFactory daf = DataAccessFactory.getInstance();
		try(Session session = daf.getSession()){
			Transaction trans = session.beginTransaction();
			UserDAO users = daf.getDAO(UserDAO.class, DatabaseType.SQLITE, session);
			User me = new User();
			me.setDiscordId("90879795365179392");
			me.setId(100000L);
			users.persist(me);
			trans.commit();
		}
	}

}
