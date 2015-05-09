package com.dao;

import java.util.List;

@SuppressWarnings({"rawtypes"})
public interface IObjectDAO {
	
	public void attachClean(Object instance);

	public void attachDirty(Object instance);

	public void delete(Object instance);

	
	public List findAll(Class arg, String order, int row, int page);

	public List findAll(Class arg);

	public List findByExample(Object instance, Class arg, String order,
			int row, int page);

	public List findByExample(Object instance, Class arg);

	public Object findById(int id, Class arg);

	public Object findById(String id, Class arg);

	public List findByProperty(String propertyName, Object value, Class arg);

	public List findByProperty(String propertyName, Object value, Class arg,
			String order, int row, int page);

	public Object merge(Object instance);

	public void save(Object instance);
	
	public void saveOrUpdate(Object instance);

	public void update(Object instance);

	public List findByLike(Class arg, String propertyName, Object value,
			String order, int row, int page);

	public List findByLike(Class arg, String propertyName, Object value);

	public Long getTotalCount(String hql);

	public List findByQuery(int pageNo, int pageSize, String hql);

	public List findBySql(int firstResult, int maxResults, String hql);
}
