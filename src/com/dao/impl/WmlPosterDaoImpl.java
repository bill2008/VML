package com.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlPosterDao;
import com.pojo.WmlPoster;
import com.tool.Constant;

public class WmlPosterDaoImpl extends BaseDAO implements IWmlPosterDao {


	@SuppressWarnings("unchecked")
	public List<WmlPoster> queryWmlPosterList(WmlPoster item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlPoster.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getName())){
				dc.add(Restrictions.like("name", "%"+item.getName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getDescription())){
				dc.add(Restrictions.like("description","%"+ item.getDescription()+"%"));
			}
			if(item.getCreateDate()!=null){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}
		
		}
		dc.add(Restrictions.eq("isDel",Constant.DELETE));
		List<WmlPoster> WmlPoster=c.list();
		return WmlPoster;
	}


}
