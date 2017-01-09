package com.crusnikatelier.ildque.data.sqlite.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.crusnikatelier.ildque.data.daos.DNestNoticeSubscriberDAO;
import com.crusnikatelier.ildque.data.entities.DNestNoticeSubscriber;
import com.crusnikatelier.ildque.data.sqlite.SqliteDAOBase;

public class DNestNoticeSubscriberDAOImpl extends SqliteDAOBase<DNestNoticeSubscriber> implements DNestNoticeSubscriberDAO{
	
	public DNestNoticeSubscriberDAOImpl() {
		super();
	}

	public DNestNoticeSubscriberDAOImpl(Session session) {
		super(session);
	}
	
	@Override
	public DNestNoticeSubscriber find(long id) {
		String q = "select d from dnestnotice_subscribers where d.id = :id";
		Query<DNestNoticeSubscriber> query = getSession().createQuery(q, DNestNoticeSubscriber.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<DNestNoticeSubscriber> findAll() {
		String q = "select d from dnestnotice_subscribers";
		Query<DNestNoticeSubscriber> query = getSession().createQuery(q, DNestNoticeSubscriber.class);
		return query.getResultList();
	}
}
