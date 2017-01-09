package com.crusnikatelier.ildque.data.sqlite.impl;

import java.util.List;

import org.hibernate.query.Query;

import com.crusnikatelier.ildque.data.daos.UserDAO;
import com.crusnikatelier.ildque.data.entities.User;
import com.crusnikatelier.ildque.data.sqlite.SqliteDAOBase;

public class UserDAOImpl extends SqliteDAOBase implements UserDAO {

	@Override
	public User find(long id) {
		String q = "select u from users u  where u.id = :id";
		Query<User> query = getSession().createQuery(q, User.class);
		return query.getSingleResult();
	}

	@Override
	public List<User> findAll() {
		String q = "select u from users u";
		Query<User> query =  getSession().createQuery(q, User.class);
		return query.getResultList();
	}

	@Override
	public void persist(User entity) {
		getSession().save(entity);
	}

	@Override
	public void update(User entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(User entity) {
		getSession().delete(entity);
	}
}
