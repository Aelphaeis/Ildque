package com.crusnikatelier.ildque.data.sqlite;

import org.hibernate.Session;

public class SqliteDAOBase {
	Session session;
	
	public SqliteDAOBase() {
		
	}
	
	public SqliteDAOBase(Session session){
		this();
		setSession(session);
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
