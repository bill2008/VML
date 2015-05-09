package com.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlProductDao;
import com.pojo.WmlProduct;
import com.tool.Constant;

public class WmlProductDaoImpl extends BaseDAO implements IWmlProductDao {

	@SuppressWarnings("unchecked")
	public List<WmlProduct> queryWmlProductList(WmlProduct item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlProduct.class);
		try{
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getCreateDate()) && !item.getCreateDate().endsWith("")){
				dc.add(Restrictions.ge("onTime", item.getCreateDate()));
			}
			if(StringUtils.isNotEmpty(item.getEndDate()) && !item.getEndDate().endsWith("")){
				dc.add(Restrictions.le("onTime", item.getEndDate()));
			}
			if(item.getId()!=null){
				dc.add(Restrictions.eq("id", item.getId()));
			}
			if(item.getPrice()!=null){
				dc.add(Restrictions.le("price", item.getPrice()));
			}
			if(item.getEndPrice()!=null){
				dc.add(Restrictions.ge("price", item.getEndPrice()));
			}
			if(item.getUid()!=null){
				dc.add(Restrictions.eq("uid", item.getUid()));
			}
			if(item.getBid()!=null){
				dc.add(Restrictions.eq("bid", item.getBid()));
			}
			if(item.getTid()!=null){
				dc.add(Restrictions.eq("tid", item.getTid()));
			}
			if(item.getOid()!=null){
				dc.add(Restrictions.eq("oid", item.getOid()));
			}
			if(StringUtils.isNotEmpty(item.getProperty()) && !item.getProperty().equals(" ")){
				dc.add(Restrictions.eq("property", item.getProperty()));
			}
			if(StringUtils.isNotEmpty(item.getName()) ){
				dc.add(Restrictions.eq("name", item.getName()));
			}
			if(StringUtils.isNotEmpty(item.getUploadType()) && !item.getUploadType().equals(" ")){
				dc.add(Restrictions.eq("uploadType", item.getUploadType()));
			}
			if(item.getForwar()!=null){
				dc.add(Restrictions.eq("forwar", item.getForwar()));
			}
			if(item.getDownload()!=null){
				dc.add(Restrictions.eq("download", item.getDownload()));
			}
			if(item.getCollect()!=null){
				dc.add(Restrictions.eq("collect", item.getCollect()));
			}
			if(item.getStatus()!=null){
				dc.add(Restrictions.eq("status", item.getStatus()));
			}	
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		dc.addOrder(Order.asc("onTime"));
		List<WmlProduct> WmlProductList=c.list();
		return WmlProductList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WmlProduct> queryPageWmlProduct(WmlProduct item, int startRowNum,
			int pageSize) {
		
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlProduct.class);
		try{
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getCreateDate()) && !item.getCreateDate().endsWith("")){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(StringUtils.isNotEmpty(item.getEndDate()) && !item.getEndDate().endsWith("")){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}
			if(item.getId()!=null){
				dc.add(Restrictions.eq("id", item.getId()));
			}
			if(item.getPrice()!=null){
				dc.add(Restrictions.le("price", item.getPrice()));
			}
			if(item.getEndPrice()!=null){
				dc.add(Restrictions.ge("price", item.getEndPrice()));
			}
			if(item.getUid()!=null){
				dc.add(Restrictions.eq("uid", item.getUid()));
			}
			if(item.getBid()!=null){
				dc.add(Restrictions.eq("bid", item.getBid()));
			}
			if(item.getTid()!=null){
				dc.add(Restrictions.eq("tid", item.getTid()));
			}
			if(item.getOid()!=null){
				dc.add(Restrictions.eq("oid", item.getOid()));
			}
			if(StringUtils.isNotEmpty(item.getProperty()) && !item.getProperty().equals(" ")){
				dc.add(Restrictions.eq("property", item.getProperty()));
			}
			if(StringUtils.isNotEmpty(item.getName()) ){
				dc.add(Restrictions.eq("name", item.getName()));
			}
			if(StringUtils.isNotEmpty(item.getUploadType()) && !item.getUploadType().equals(" ")){
				dc.add(Restrictions.eq("uploadType", item.getUploadType()));
			}
			if(item.getForwar()!=null){
				dc.add(Restrictions.eq("forwar", item.getForwar()));
			}
			if(item.getDownload()!=null){
				dc.add(Restrictions.eq("download", item.getDownload()));
			}
			if(item.getCollect()!=null){
				dc.add(Restrictions.eq("collect", item.getCollect()));
			}
			if(item.getStatus()!=null){
				dc.add(Restrictions.eq("status", item.getStatus()));
			}	
		}
		dc.add(Restrictions.eq("isDel", Constant.DELETE));
		c.setFirstResult(startRowNum);
		c.setMaxResults(pageSize);
		List<WmlProduct> WmlProductList= c.list();
		return WmlProductList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int getProductCount(WmlProduct item){
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer sql=new StringBuffer("select COUNT(*) FROM wml_product WHERE isDel=1 ");
		
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getCreateDate()) && !item.getCreateDate().endsWith("")){
				sql.append(" and createDate>= '"+item.getCreateDate()+"'");
			}
			if(StringUtils.isNotEmpty(item.getEndDate()) && !item.getEndDate().endsWith("")){
				sql.append(" and createDate <= '"+item.getEndDate()+"'");
			}
			if(item.getId()!=null){
				sql.append(" and id= "+item.getId()+"");
			}
			if(item.getPrice()!=null){
				sql.append(" and price>= "+item.getPrice()+"");
			}
			if(item.getEndPrice()!=null){
				sql.append(" and price<= "+item.getEndPrice()+"");
			}
			if(item.getUid()!=null){
				sql.append(" and uid= "+item.getUid()+"");
			}
			if(item.getBid()!=null){
				sql.append(" and bid= "+item.getBid()+"");
			}
			if(item.getTid()!=null){
				sql.append(" and tid= "+item.getTid()+"");
			}
			if(item.getOid()!=null){
				sql.append(" and oid= "+item.getOid()+"");
			
			}
			if(StringUtils.isNotEmpty(item.getProperty()) && !item.getProperty().equals(" ")){
				sql.append(" and property= '"+item.getProperty()+"'");
				
			}
			if(StringUtils.isNotEmpty(item.getName()) ){
				sql.append(" and name= '"+item.getName()+"'");
			}
			if(StringUtils.isNotEmpty(item.getUploadType()) && !item.getUploadType().equals(" ")){
				sql.append(" and uploadType= '"+item.getUploadType()+"'");
			}
		}
		Query query=session.createSQLQuery(sql.toString());
		
		 String str = query.uniqueResult().toString();
		 return Integer.valueOf(str);
	}
	

}
