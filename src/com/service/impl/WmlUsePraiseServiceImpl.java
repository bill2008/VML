package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlUsePraiseDao;
import com.pojo.WmlUsePraise;
import com.service.IWmlUsePraiseService;
import com.tool.Constant;

public class WmlUsePraiseServiceImpl implements IWmlUsePraiseService {

	private IWmlUsePraiseDao wmlUsePraiseDao;
	
	
	public void setWmlUsePraiseDao(IWmlUsePraiseDao wmlUsePraiseDao) {
		this.wmlUsePraiseDao = wmlUsePraiseDao;
	}

	@Override
	public List<WmlUsePraise> queryWmlUsePraiseList(WmlUsePraise item) {
		// TODO Auto-generated method stub
		return wmlUsePraiseDao.queryWmlUsePraiseList(item);
	}

	@Override
	public WmlUsePraise queryWmlUsePraise(WmlUsePraise item) {
		List<WmlUsePraise>  WmlUsePraiseList=wmlUsePraiseDao.queryWmlUsePraiseList(item);
		if(WmlUsePraiseList.size()>0){
			return WmlUsePraiseList.get(0);
		}
		return null;
	}

	@Transactional
	public String addWmlUsePraise(WmlUsePraise item) {
		String message=null;
		try{
			item.setIsDel(Constant.DELETE);
			wmlUsePraiseDao.save(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

	@Transactional
	public String updateWmlUsePraise(WmlUsePraise item) {
		String message=null;
		try{
			wmlUsePraiseDao.update(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

}
