package com.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlOrganDao;
import com.pojo.WmlOrgan;
import com.tool.Constant;

public class WmlOrganDaoImpl extends BaseDAO implements IWmlOrganDao {

	
	@SuppressWarnings("unchecked")
	public List<WmlOrgan> queryWmlOrganList(WmlOrgan item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlOrgan.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getCode())){
				dc.add(Restrictions.like("code", "%"+item.getCode()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getName())){
				dc.add(Restrictions.like("name", "%"+item.getName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getAddress())){
				dc.add(Restrictions.like("address", "%"+item.getAddress()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getPhone())){
				dc.add(Restrictions.like("phone","%"+ item.getPhone()+"%"));
			}
			if(item.getCreateDate()!=null){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}
			if(item.getUserId()!=null){
				dc.add(Restrictions.eq("userId", item.getUserId()));
			}
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		List<WmlOrgan> WmlOrganlist=c.list();
		return WmlOrganlist;
	}

}
