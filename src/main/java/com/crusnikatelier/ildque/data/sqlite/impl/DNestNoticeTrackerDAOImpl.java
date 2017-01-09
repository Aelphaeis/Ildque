package com.crusnikatelier.ildque.data.sqlite.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.crusnikatelier.ildque.data.daos.DNestNoticeTrackerDAO;
import com.crusnikatelier.ildque.data.entities.DNestNoticeTracker;
import com.crusnikatelier.ildque.data.sqlite.SqliteDAOBase;

public class DNestNoticeTrackerDAOImpl extends SqliteDAOBase<DNestNoticeTracker>  implements DNestNoticeTrackerDAO {

	public DNestNoticeTrackerDAOImpl() {
		super();
	}

	public DNestNoticeTrackerDAOImpl(Session session) {
		super(session);
	}
	
	
	@Override
	public DNestNoticeTracker find(long id) {
		String q = "select d from dnestnotice_tracker d where d.id = :id";
		Query<DNestNoticeTracker> query =  getSession().createQuery(q, DNestNoticeTracker.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<DNestNoticeTracker> findAll() {
		String q = "select d from dnestnotice_tracker d";
		Query<DNestNoticeTracker> query =  getSession().createQuery(q, DNestNoticeTracker.class);
		return query.getResultList();

	}

}
