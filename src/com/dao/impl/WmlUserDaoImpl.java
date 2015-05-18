package com.dao.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.dao.IWmlUserDao;
import com.pojo.WmlUser;
import com.tool.Constant;


public class WmlUserDaoImpl extends BaseDAO implements IWmlUserDao {

	@SuppressWarnings("unchecked")
	public List<WmlUser> queryWmlUserList(WmlUser item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlUser.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(item.getCreateDate()!=null){

				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				
				dc.add(Restrictions.le("createDate",item.getEndDate()));
			}
			if(item.getLastDate()!=null){
				dc.add(Restrictions.ge("lastDate", item.getLastDate()));
			}
			if(item.getLastTime()!=null){
				dc.add(Restrictions.le("lastTime", item.getLastTime()));
			}
			if(item.getId()!=null){
				dc.add(Restrictions.ge("id", item.getId()));
			}
			if(item.getUid()!=null){
				dc.add(Restrictions.eq("uid", item.getUid()));
			}
			if(item.getStatus()!=null){
				dc.add(Restrictions.eq("status", item.getStatus()));
			}
			
			if(StringUtils.isNotEmpty(item.getLoginName())){
				dc.add(Restrictions.like("loginName","%"+item.getLoginName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getName())){
				dc.add(Restrictions.like("name", "%"+item.getName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getPassword())){
				dc.add(Restrictions.eq("password", item.getPassword()));
			}
			if(StringUtils.isNotEmpty(item.getPhone())){
				dc.add(Restrictions.like("phone","%"+ item.getPhone()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getType())){
				dc.add(Restrictions.eq("type", item.getType()));
			}
			if(item.getOrgan()!=null){
				dc.add(Restrictions.eq("organ", item.getOrgan()));
			}
			if(item.getPermissions()!=null){
				dc.add(Restrictions.eq("permissions", item.getPermissions()));
			}
			if(item.getChannel()!=null){
				dc.add(Restrictions.eq("channel", item.getChannel()));
			}
			if(StringUtils.isNotEmpty(item.getUploadFlag())){
				dc.add(Restrictions.eq("uploadFlag", item.getUploadFlag()));
			}
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		List<WmlUser> WmlUserList=c.list();
		return WmlUserList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WmlUser> queryWmlUserPage(WmlUser item, int startRowNum,
			int pageSize) {
		/*Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlUser.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(item.getCreateDate()!=null){

				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				
				dc.add(Restrictions.le("createDate",item.getEndDate()));
			}
			if(item.getLastDate()!=null){
				dc.add(Restrictions.ge("lastDate", item.getLastDate()));
			}
			if(item.getLastTime()!=null){
				dc.add(Restrictions.le("lastTime", item.getLastTime()));
			}
			if(item.getId()!=null){
				dc.add(Restrictions.ge("id", item.getId()));
			}
			if(item.getUid()!=null){
				dc.add(Restrictions.eq("uid", item.getUid()));
			}
			if(item.getStatus()!=null){
				dc.add(Restrictions.eq("status", item.getStatus()));
			}
			
			if(StringUtils.isNotEmpty(item.getLoginName())){
				dc.add(Restrictions.like("loginName","%"+item.getLoginName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getName())){
				dc.add(Restrictions.like("name", "%"+item.getName()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getPassword())){
				dc.add(Restrictions.eq("password", item.getPassword()));
			}
			if(StringUtils.isNotEmpty(item.getPhone())){
				dc.add(Restrictions.like("phone","%"+ item.getPhone()+"%"));
			}
			if(StringUtils.isNotEmpty(item.getType())){
				dc.add(Restrictions.eq("type", item.getType()));
			}
			if(item.getOrgan()!=null){
				dc.add(Restrictions.eq("organ", item.getOrgan()));
			}
			if(item.getPermissions()!=null){
				dc.add(Restrictions.eq("permissions", item.getPermissions()));
			}
			if(item.getChannel()!=null){
				dc.add(Restrictions.eq("channel", item.getChannel()));
			}
			if(StringUtils.isNotEmpty(item.getUploadFlag())){
				dc.add(Restrictions.eq("uploadFlag", item.getUploadFlag()));
			}
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		c.setFirstResult(startRowNum-1);
		c.setMaxResults(pageSize);
		List<WmlUser> WmlUserList=c.list();
		return WmlUserList;		*/
	Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		//hql语句 WmlUser 是pojo类的类名
		StringBuffer sql=new StringBuffer();
		sql.append(" from WmlUser ");
		sql.append("where isDel=1 ");
		
		if(item!=null){
			if(item.getCreateDate()!=null){
				sql.append(" and createDate>= '"+item.getCreateDate()+"'");
			}
			if(item.getEndDate()!=null){
				sql.append(" and createDate <= '"+item.getEndDate()+"'");
			}
			if(item.getLastDate()!=null){
				sql.append(" and lastDate>= '"+item.getCreateDate()+"'");
				
			}
			if(item.getLastTime()!=null){
				sql.append(" and lastDate <= '"+item.getEndDate()+"'");
			}
			if(item.getId()!=null){
				sql.append(" and id like '"+item.getId()+"%'");
			}
			if(item.getUid()!=null){
				sql.append(" and uid= "+item.getUid()+"");
			}
			if(item.getStatus()!=null){
				sql.append(" and status= "+item.getStatus()+"");
			}
			if(StringUtils.isNotEmpty(item.getLoginName())){
				
				sql.append(" and loginName like '%"+item.getLoginName()+"%'");
			}
			if(StringUtils.isNotEmpty(item.getName())){
				sql.append(" and name= '"+item.getName()+"'");
			}
			if(StringUtils.isNotEmpty(item.getPassword())){
				sql.append(" and password= '"+item.getUid()+"'");
			}
			if(StringUtils.isNotEmpty(item.getPhone())){
				sql.append(" and phone like '%"+item.getUid()+"%'");
			}
			if(StringUtils.isNotEmpty(item.getType())){
				sql.append(" and type= '"+item.getType()+"'");
			}
			if(item.getOrgan()!=null){
				sql.append(" and Organ= "+item.getOrgan()+"");
			}
			if(item.getPermissions()!=null){
				sql.append(" and Permissions= "+item.getPermissions()+"");
			}
			if(item.getChannel()!=null){
				sql.append(" and Channel= "+item.getChannel()+"");
			}
			if(StringUtils.isNotEmpty(item.getUploadFlag())){
				sql.append(" and uploadFlag= '"+ item.getUploadFlag()+"'");
			}
		}
		
		Query query=session.createQuery(sql.toString());
		query.setFirstResult(startRowNum-1);
		query.setMaxResults(pageSize);
		
		List<WmlUser> wmlUserList=query.list();
		return wmlUserList;
	}
	
	@Override
	public int updateWmlUserPermissions(WmlUser item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer sql=new StringBuffer("update wml_user set ");
		if(item!=null){
			if(item.getPermissions()!=null){
				sql.append(" Permissions = "+item.getPermissions()+"");
			}
			if(item.getId()!=null){
				sql.append(" where id = "+item.getId()+"");
			}
		}
		Query query=session.createSQLQuery(sql.toString());
		
		int result = query.executeUpdate();
		return result;
	}
	
	
	
	@Override
	// 这里使用的是hql语句
	public int getWmlUserCount(WmlUser item) {
	Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer sql=new StringBuffer("select count(*) ");		
		sql.append(" from WmlUser ");
		sql.append("where isDel=1 ");
		if(item!=null){
			if(item.getCreateDate()!=null){
				sql.append(" and createDate>= '"+item.getCreateDate()+"'");
			}
			if(item.getEndDate()!=null){
				sql.append(" and createDate <= '"+item.getEndDate()+"'");
			}
			if(item.getLastDate()!=null){
				sql.append(" and lastDate>= '"+item.getCreateDate()+"'");
				
			}
			if(item.getLastTime()!=null){
				sql.append(" and lastDate <= '"+item.getEndDate()+"'");
			}
			if(item.getId()!=null){
				sql.append(" and id like '"+item.getId()+"%'");
			}
			if(item.getUid()!=null){
				sql.append(" and uid= "+item.getUid()+"");
			}
			if(item.getStatus()!=null){
				sql.append(" and status= "+item.getStatus()+"");
			}
			if(StringUtils.isNotEmpty(item.getLoginName())){
				
				sql.append(" and loginName like '%"+item.getLoginName()+"%'");
			}
			if(StringUtils.isNotEmpty(item.getName())){
				sql.append(" and name= '"+item.getName()+"'");
			}
			if(StringUtils.isNotEmpty(item.getPassword())){
				sql.append(" and password= '"+item.getUid()+"'");
			}
			if(StringUtils.isNotEmpty(item.getPhone())){
				sql.append(" and phone like '%"+item.getUid()+"%'");
			}
			if(StringUtils.isNotEmpty(item.getType())){
				sql.append(" and type= '"+item.getType()+"'");
			}
			if(item.getOrgan()!=null){
				sql.append(" and Organ= "+item.getOrgan()+"");
			}
			if(item.getPermissions()!=null){
				sql.append(" and Permissions= "+item.getPermissions()+"");
			}
			if(item.getChannel()!=null){
				sql.append(" and Channel= "+item.getChannel()+"");
			}
			if(StringUtils.isNotEmpty(item.getUploadFlag())){
				sql.append(" and uploadFlag= '"+ item.getUploadFlag()+"'");
			}
		}
		Query query=session.createQuery(sql.toString());
		
		 String str = query.uniqueResult().toString();
		 return Integer.valueOf(str);
	}

}
