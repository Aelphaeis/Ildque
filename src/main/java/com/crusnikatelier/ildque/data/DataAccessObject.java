package com.crusnikatelier.ildque.data;

import java.util.List;

public interface DataAccessObject <T> {
	public T find(long id);
	public List<T> findAll();
	public void persist(T entity);
	public void update(T entity);
	public void delete(T entity);
}
