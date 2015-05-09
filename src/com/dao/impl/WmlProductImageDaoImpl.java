package com.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlProductImageDao;
import com.pojo.WmlProductImage;
import com.tool.Constant;

public class WmlProductImageDaoImpl extends BaseDAO implements
		IWmlProductImageDao {

	@SuppressWarnings("unchecked")
	public List<WmlProductImage> queryWmlProductImageList(WmlProductImage item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlProductImage.class);
		Criteria c = dc.getExecutableCriteria(session);
		try{
		if(item!=null){
			if(item.getId()!=null){
				dc.add(Restrictions.eq("id", item.getId()));
			}
			if(item.getProductId()!=null){
				dc.add(Restrictions.eq("productId", item.getProductId()));
			}
			if(item.getIsFirst()!=null){
				dc.add(Restrictions.eq("isFirst", item.getIsFirst()));
			}
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		List<WmlProductImage> WmlProductImageList=c.list();
		return WmlProductImageList;
		}catch(Exception  e){
			e.printStackTrace();
			return null;
		}
	}

}
