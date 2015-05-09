package com.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlMenuDAO;
import com.pojo.WmlMenu;

@SuppressWarnings("unchecked")
public class WmlMenuDAOImpl extends BaseDAO implements IWmlMenuDAO  {

	//获取所有菜单信息
	public List<WmlMenu> AllMenu() {
		
		return this.findAll(WmlMenu.class,"menuId");
	}
	
	
	//根据字段升序排列
	@SuppressWarnings("rawtypes")
	public List findAll(Class arg,String order) {
		try{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(arg);
			if(order!=null)
			{
				criteria.addOrder(Order.asc(order));
			}
			List list=criteria.list();
			return list;
		}catch(RuntimeException re){
			throw re;
		}	
	}
	
	public List<WmlMenu> queryMeunbyaId(int menuAid) {
		try{
				String sql="select * from wml_menu where menu_id="+menuAid+" or menu_aid=1 or menu_aid in(select menu_id FROM wml_menu where menu_aid="+menuAid+")";
				Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
				SQLQuery query=session.createSQLQuery(sql).addEntity(WmlMenu.class);
				 List<WmlMenu> menuList=query.list();
				return menuList;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}


	@Override
	public List<WmlMenu> queryMenu(WmlMenu item) {
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlMenu.class);
		Criteria c = dc.getExecutableCriteria(session);
		if(item!=null){
			if(StringUtils.isNotEmpty(item.getMenuName())){
				dc.add(Restrictions.like("menuName", "%"+item.getMenuName()));
			}
			if(StringUtils.isNotEmpty(item.getMenuLink())){
				dc.add(Restrictions.like("menuLink", "%"+item.getMenuLink()+"%"));
			}
		}
		List<WmlMenu> WmlMenuList=c.list();
		return WmlMenuList;
	}
	
}
