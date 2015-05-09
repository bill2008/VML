package com.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlBrandDao;
import com.pojo.WmlBrand;
import com.tool.Constant;


public class WmlBrandDaoImpl extends BaseDAO implements IWmlBrandDao {

	@SuppressWarnings("unchecked")
	public List<WmlBrand> queryWmlBrandList(WmlBrand item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlBrand.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getLetter())){
				dc.add(Restrictions.like("letter", "%"+item.getLetter()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getKeyword())){
				dc.add(Restrictions.like("keyword", "%"+item.getKeyword()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getName())){
				dc.add(Restrictions.like("name","%"+ item.getName()+"%"));
			}
			if(item.getCreateDate()!=null){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
				
			}
			if(item.getEndDate()!=null){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}

		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));	
		List<WmlBrand> WmlBrandlist=c.list();
		return WmlBrandlist;
	}

}
