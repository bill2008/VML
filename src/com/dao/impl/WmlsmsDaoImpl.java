package com.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlsmsDao;
import com.pojo.Wmlsms;
import com.tool.Constant;

public class WmlsmsDaoImpl extends BaseDAO implements IWmlsmsDao {

	@SuppressWarnings("unchecked")
	public List<Wmlsms> queryWmlsmsList(Wmlsms item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(Wmlsms.class);
		Criteria c = dc.getExecutableCriteria(session);
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
			if(StringUtils.isNotEmpty(item.getContent()) && item.getContent()!=null){
				dc.add(Restrictions.like("content", "%"+item.getContent()+"%"));
			}
			if(item.getUid()!=null){
				dc.add(Restrictions.eq("uid", item.getUid()));
			}
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		List<Wmlsms> WmlsmsUserList=c.list();
		return WmlsmsUserList;
	}

}
