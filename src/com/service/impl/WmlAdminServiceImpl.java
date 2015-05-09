package com.service.impl;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlAdminDao;
import com.pojo.WmlAdmin;
import com.service.IWmlAdminService;
import com.tool.Constant;
import com.tool.MD5Util;


public class WmlAdminServiceImpl implements IWmlAdminService {

	private IWmlAdminDao wmlAdminDao;
	
	public void setWmlAdminDao(IWmlAdminDao wmlAdminDao) {
		this.wmlAdminDao = wmlAdminDao;
	}

	/**
	 * 查询所有用户信息
	 */
	public List<WmlAdmin> queryWmlAdminList(WmlAdmin item) {
		
		return wmlAdminDao.queryWmlAdminList(item);
	}


	/**
	 * 查询单个管理员信息
	 */
	
	public WmlAdmin queryWmlAdmin(WmlAdmin item) {
		 List<WmlAdmin> adminList=wmlAdminDao.queryWmlAdminList(item);
		 if(adminList.size()>0){
			 return adminList.get(0);
		 }
		return null;
	}

	@Transactional
	public boolean updateWmlAdmin(WmlAdmin item) {
		try{
			wmlAdminDao.update(item);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean addWmlAdmin(WmlAdmin item) {
		try{
			item.setPassword(MD5Util.getMD5("123456"));
			item.setStatus(Constant.DELETE);
			wmlAdminDao.save(item);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}




}
