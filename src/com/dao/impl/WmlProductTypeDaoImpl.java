package com.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlProductTypeDao;
import com.pojo.WmlProductType;
import com.tool.Constant;

public class WmlProductTypeDaoImpl extends BaseDAO implements
		IWmlProductTypeDao {

	@SuppressWarnings("unchecked")
	public List<WmlProductType> queryWmlProductTypeList(WmlProductType item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlProductType.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(item.getId()!=null){
				dc.add(Restrictions.eq("id",item.getId()));
			}
		}
		dc.add(Restrictions.eq("isDel",Constant.DELETE));
		List<WmlProductType> WmlProductTypeList=c.list();
		return WmlProductTypeList;
	}

}
