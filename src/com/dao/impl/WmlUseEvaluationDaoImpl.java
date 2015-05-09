package com.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dao.IWmlUseEvaluationDao;
import com.pojo.WmlUseEvaluation;
import com.tool.Constant;

public class WmlUseEvaluationDaoImpl extends BaseDAO implements IWmlUseEvaluationDao {

	@SuppressWarnings("unchecked")
	public List<WmlUseEvaluation> queryWmlUseEvaluationList(WmlUseEvaluation item) {
		List<WmlUseEvaluation> WmlUseEvaluationlist=null;
		try{
		Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		DetachedCriteria dc=DetachedCriteria.forClass(WmlUseEvaluation.class);
		if(item!=null)
		{
			if(item.getId()!=null){
				dc.add(Restrictions.eq("id",item.getId()));
			}
			if(item.getProductId()!=null){
				dc.add(Restrictions.eq("productId",item.getProductId()));
			}
			if(item.getAuthorId()!=null){
				dc.add(Restrictions.eq("authorId",item.getAuthorId()));
			}
			if(item.getParentId()!=null){
				dc.add(Restrictions.eq("parentId",item.getParentId()));
			}
			if(item.getNoticeParentIdFlag()!=null){
				dc.add(Restrictions.eq("noticeParentIdFlag",item.getNoticeParentIdFlag()));
			}
			if(item.getNoticeProductOwnerFlag()!=null){
				dc.add(Restrictions.eq("noticeProductOwnerFlag",item.getNoticeProductOwnerFlag()));
			}
			if(item.getContent()!=null){
				dc.add(Restrictions.like("noticeProductOwnerFlag","%"+item.getContent()+"%"));
			}
			if(item.getIsDel()!=null){
				dc.add(Restrictions.eq("isDel",item.getIsDel()));
			}
			if(item.getCreateDate()!=null){
				dc.add(Restrictions.ge("createDate", item.getCreateDate()));
			}
			if(item.getEndDate()!=null){
				dc.add(Restrictions.le("createDate", item.getEndDate()));
			}
		}
		dc.add(Restrictions.eq("isDel",Constant.DELETE));
		Criteria c = dc.getExecutableCriteria(session);
		WmlUseEvaluationlist=c.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return WmlUseEvaluationlist;
	}



}
