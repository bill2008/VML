package com.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlAdvertisementDao;
import com.pojo.WmlAdvertisement;
import com.tool.Constant;

public class WmlAdvertisementDaoImpl extends BaseDAO implements
		IWmlAdvertisementDao {

	
	@SuppressWarnings("unchecked")
	public List<WmlAdvertisement> queryWmlAdvertisementList(
			WmlAdvertisement item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlAdvertisement.class);
		if(item!=null){
			if(item.getCreateDate()!=null){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}
			if(!item.getDescription().equals("")){
				dc.add(Restrictions.like("description","%"+item.getDescription()+"%"));
			}
			if(!item.getName().equals("")){
				dc.add(Restrictions.like("name","%"+ item.getName()+"%"));
			}
		}
		dc.add(Restrictions.eq("isDel",Constant.DELETE));
		Criteria c = dc.getExecutableCriteria(session);
		List<WmlAdvertisement> WmlAdverlist=c.list();
		return WmlAdverlist;
	}

}
