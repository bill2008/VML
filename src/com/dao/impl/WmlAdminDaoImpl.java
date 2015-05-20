package com.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlAdminDao;
import com.pojo.WmlAdmin;
import com.tool.Constant;

public class WmlAdminDaoImpl extends BaseDAO implements IWmlAdminDao {

	@SuppressWarnings("unchecked")
	public List<WmlAdmin> queryWmlAdminList(WmlAdmin item) {
		List<WmlAdmin> WmlAdminlist=null;
		try{
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlAdmin.class);
		if(item!=null)
		{
			if(StringUtils.isNotEmpty(item.getLoginName())){
				dc.add(Restrictions.like("loginName","%"+ item.getLoginName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getName())){
				dc.add(Restrictions.like("name","%"+item.getName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getPassword())){
				dc.add(Restrictions.eq("password",item.getPassword()));
			}
			if(StringUtils.isNotEmpty(item.getPhone())){
				dc.add(Restrictions.like("phone","%"+ item.getPhone()+"%"));
			}
		}
		/*dc.add(Restrictions.eq("isDel",Constant.DELETE));*/
		Criteria c = dc.getExecutableCriteria(session);
		WmlAdminlist=c.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return WmlAdminlist;
	}

}
