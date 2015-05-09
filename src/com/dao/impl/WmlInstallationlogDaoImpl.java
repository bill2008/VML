package com.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlInstallationlogDao;
import com.pojo.ClinetInsertType;
import com.pojo.WmlInstallationlog;
import com.tool.Constant;

public class WmlInstallationlogDaoImpl extends BaseDAO implements
		IWmlInstallationlogDao {

	@SuppressWarnings("unchecked")
	public List<WmlInstallationlog> queryWmlInstallationlogList(
			WmlInstallationlog item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlInstallationlog.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getMac())){
				dc.add(Restrictions.like("mac", "%"+item.getMac()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getRegion()) ){
				dc.add(Restrictions.like("region","%"+ item.getRegion()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getType())&& !item.getType().equals(" ")){
				dc.add(Restrictions.like("type","%"+ item.getType()+"%"));
			}
			if(item.getCreateDate()!=null){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		List<WmlInstallationlog> WmlInstalist=c.list();
		return WmlInstalist;
	}

	
	@SuppressWarnings("rawtypes")
	public List<ClinetInsertType> queryInstallClientType(String clientType) {
		try{
			StringBuffer sql=new StringBuffer();
			sql.append("select count(*) as count,type from wml_installationlog where isDel=1 group by type ");
			
			if(clientType!=null && StringUtils.isNotEmpty(clientType)){
				sql.append("HAVING type like '%"+clientType+"%'");
			}
			
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Iterator it=session.createSQLQuery(sql.toString()).list().iterator();
			List<ClinetInsertType> clientInsertTypeList= new ArrayList<ClinetInsertType>();
			while(it.hasNext()) 
			{ 
			Object[] row = (Object[])it.next(); 
			ClinetInsertType item=new ClinetInsertType();
			item.setCount(Integer.valueOf(row[0].toString()));
			item.setType((String)row[1]);
			clientInsertTypeList.add(item);
			} 
			return clientInsertTypeList;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
