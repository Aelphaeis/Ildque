package com.crusnikatelier.ildque.data.sqlite;

import org.hibernate.Session;

import com.crusnikatelier.ildque.data.DataAccessObject;

public abstract class SqliteDAOBase<T> implements DataAccessObject<T>{
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
	
	@Override
	public void persist(T entity) {
		getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

}
