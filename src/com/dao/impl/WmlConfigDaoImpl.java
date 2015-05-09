package com.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlConfigDao;
import com.pojo.WmlConfig;
import com.tool.Constant;

public class WmlConfigDaoImpl extends BaseDAO implements IWmlConfigDao {

	@SuppressWarnings("unchecked")
	public List<WmlConfig> queryWmlConfigList(WmlConfig item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlConfig.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getName())){
				dc.add(Restrictions.like("name", "%"+item.getName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getDescription()) ){
				dc.add(Restrictions.like("description","%"+ item.getDescription()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getValue())){
				dc.add(Restrictions.like("value","%"+ item.getValue()+"%"));
			}
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		List<WmlConfig> WmlConfiglist=c.list();
		return WmlConfiglist;
	}

}
