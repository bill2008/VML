package com.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlUseCollectDao;
import com.pojo.WmlUseCollect;
import com.tool.Constant;

public class WmlUseCollectDaoImpl extends BaseDAO implements IWmlUseCollectDao {

	@SuppressWarnings("unchecked")
	public List<WmlUseCollect> queryWmlUseCollectList(WmlUseCollect item) {
		List<WmlUseCollect> WmlUseCollectlist=null;
		try{
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlUseCollect.class);
		if(item!=null)
		{
			if(item.getId()!=null){
				dc.add(Restrictions.eq("id",item.getId()));
			}
			if(item.getProductId()!=null){
				dc.add(Restrictions.eq("productId",item.getProductId()));
			}
			if(item.getUserId()!=null){
				dc.add(Restrictions.eq("userId",item.getUserId()));
			}
			if(item.getIsDel()!=null){
				dc.add(Restrictions.eq("isDel",item.getIsDel()));
			}
			if(item.getCreateDate()!=null){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}
		}
		dc.add(Restrictions.eq("isDel",Constant.DELETE));
		Criteria c = dc.getExecutableCriteria(session);
		WmlUseCollectlist=c.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return WmlUseCollectlist;
	}



}
