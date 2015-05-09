package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlOrganDao;
import com.pojo.WmlOrgan;
import com.service.IWmlOrganService;
import com.tool.Constant;
import com.tool.TimeUtil;


public class WmlOrganServiceImpl implements IWmlOrganService {

	private IWmlOrganDao wmlOrganDao;
	
	public void setWmlOrganDao(IWmlOrganDao wmlOrganDao) {
		this.wmlOrganDao = wmlOrganDao;
	}

	@Override
	public List<WmlOrgan> queryWmlOrganList(WmlOrgan item) {
	
		return wmlOrganDao.queryWmlOrganList(item);
	}

	@Override
	public WmlOrgan queryWmlOrgan(WmlOrgan item) {
		List<WmlOrgan> WmlOrganList=wmlOrganDao.queryWmlOrganList(item);
		if(WmlOrganList.size()>0){
			return WmlOrganList.get(0);
		}else{
		return null;
		}
	}

	@Transactional
	public boolean updateWmlOrgan(WmlOrgan item) {
		try{
			wmlOrganDao.update(item);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean addWmlOrgan(WmlOrgan item) {
		try{
				item.setIsDel(Constant.DELETE);
				item.setCreateDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
				
				wmlOrganDao.save(item);
				return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
