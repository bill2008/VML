package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlUserDao;
import com.pojo.WmlUser;
import com.service.IWmlUserService;
import com.tool.Constant;
import com.tool.MD5Util;
import com.tool.TimeUtil;


public class WmlUserServiceImpl implements IWmlUserService {

	private IWmlUserDao wmlUserDao;
	
	public void setWmlUserDao(IWmlUserDao wmlUserDao) {
		this.wmlUserDao = wmlUserDao;
	}

	@Override
	public List<WmlUser> queryWmlUserList(WmlUser item) {
		// TODO Auto-generated method stub
		return wmlUserDao.queryWmlUserList(item);
	}

	@Override
	public WmlUser queryWmlUser(WmlUser item) {
		List<WmlUser> WmlUserList=wmlUserDao.queryWmlUserList(item);
		if(WmlUserList.size()>0){
			return WmlUserList.get(0);
		}else{
			return null;
		}
	}


	@Transactional
	public String addWmlUser(WmlUser item) {
		String message=null;
		try{
			item.setIsDel(Constant.DELETE);
			item.setCreateDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			item.setPassword(MD5Util.getMD5("123456"));
			wmlUserDao.save(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

	@Transactional
	public String updateWmlUser(WmlUser item) {
		String message=null;
		try{
			wmlUserDao.update(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public int getWmlUserCount(WmlUser item) {
		// TODO Auto-generated method stub
		return wmlUserDao.getWmlUserCount(item);
	}

	@Override
	public List<WmlUser> queryWmlUserPage(WmlUser item, int startRowNum,
			int pageSize) {
		// TODO Auto-generated method stub
		return wmlUserDao.queryWmlUserPage(item, startRowNum, pageSize);
	}

}
