package com.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;


@SuppressWarnings({"unused","rawtypes"})
public class BaseDAO extends HibernateDaoSupport {
	
	public void attachClean(Object instance) {
		try {
			getHibernateTemplate().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Object instance) {
		try {
			getHibernateTemplate().load(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Object instance) {
		try {
			getHibernateTemplate().delete(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	
	
	public List findAll(Class arg, String order, int row, int page) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			Criteria criteria = session.createCriteria(arg);
			if (order != null) {
				criteria.addOrder(Order.desc(order));
			}
			if (row != 0 && page != 0) {
				criteria.setMaxResults(row);
				criteria.setFirstResult((page - 1) * row);
			}
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findAll(Class arg) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			return session.createCriteria(arg).list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByExample(Object instance, Class arg, String order,
			int row, int page) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			Criteria criteria = session.createCriteria(arg);
			criteria.add(Example.create(instance));
			criteria.addOrder(Order.desc(order));
			criteria.setFirstResult((page - 1) * row);
			criteria.setMaxResults(row);
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByExample(Object instance, Class arg) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			Criteria criteria = session.createCriteria(arg);
			criteria.add(Example.create(instance));
			;
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Object findById(int id, Class arg) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			return session.get(arg, id);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Object findById(String id, Class arg) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			return session.get(arg, id);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value, Class arg) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			
			List<Object> results = new ArrayList<Object>();
			return session.createCriteria(arg)
					.add(Restrictions.eq(propertyName, value)).list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value, Class arg,
			String order, int row, int page) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			Criteria criteria = session.createCriteria(arg);
			criteria.add(Restrictions.eq(propertyName, value));
			criteria.addOrder(Order.desc(order));
			criteria.setFirstResult((page - 1) * row);
			criteria.setMaxResults(row);
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Object merge(Object instance) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			return session.merge(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void save(Object instance) {
		try {
			getHibernateTemplate().save(instance);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public void saveOrUpdate(Object instance){
		try{
			getHibernateTemplate().saveOrUpdate(instance);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void update(Object instance) {
		try {
			getHibernateTemplate().update(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByLike(Class arg, String propertyName, Object value,
			String order, int row, int page) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			Criteria criteria = session.createCriteria(arg);
			if (order != null) {
				criteria.addOrder(Order.desc(order));
			}
			if (row != 0 && page != 0) {
				criteria.setMaxResults(row);
				criteria.setFirstResult((page - 1) * row);
			}
			criteria.add(Restrictions.like(propertyName, "%" + value + "%"));
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByLike(Class arg, String propertyName, Object value) {
		try {
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			Criteria criteria = session.createCriteria(arg);
			criteria.add(Restrictions.like(propertyName, "%" + value + "%"));
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Long getTotalCount(String hql) {
		try {
			Query query = this.currentSession().createQuery(hql);
			Long i = (Long) query.list().get(0);
			return i;
		} catch (RuntimeException re) {
			throw re;
		}

	}

	public List findByQuery(int pageNo, int pageSize, String hql) {
		List result = null;
		try {
			Query query = this.currentSession().createQuery(hql);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			result = query.list();

		} catch (RuntimeException re) {
			throw re;
		}
		return result;
	}

	public List findBySql(int firstResult, int maxResults, String hql) {
		List result = null;
		try {
			Query query = this.currentSession().createQuery(hql);

			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);

			result = query.list();

		} catch (RuntimeException re) {
			throw re;
		}
		return result;
	}
}
