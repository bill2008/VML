package com.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlAttentionDao;
import com.pojo.WmlAttention;
import com.tool.Constant;

public class WmlAttentionDaoImpl extends BaseDAO implements IWmlAttentionDao {

	@SuppressWarnings("unchecked")
	public List<WmlAttention> queryWmlAttentionList(WmlAttention item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlAttention.class);
		if(item!=null){
			if(item.getCreateDate()!=null){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}
			if(item.getId()!=null){
				dc.add(Restrictions.eq("id", item.getId()));
			}
			if(item.getById()!=null){
				dc.add(Restrictions.eq("byId", item.getById()));
			}
			if(item.getForId()!=null){
				dc.add(Restrictions.eq("forId", item.getForId()));
			}
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		
		Criteria c = dc.getExecutableCriteria(session);
		List<WmlAttention> WmlAttentionlist=c.list();
		return WmlAttentionlist;
	}

}
