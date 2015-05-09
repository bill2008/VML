package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlUseCollectDao;
import com.pojo.WmlUseCollect;
import com.service.IWmlUseCollectService;
import com.tool.Constant;

public class WmlUseCollectServiceImpl implements IWmlUseCollectService {
	
	private IWmlUseCollectDao wmlUseCollectDao;
	

	public void setWmlUseCollectDao(IWmlUseCollectDao wmlUseCollectDao) {
		this.wmlUseCollectDao = wmlUseCollectDao;
	}

	@Override
	public List<WmlUseCollect> queryWmlUseCollectList(WmlUseCollect item) {
		// TODO Auto-generated method stub
		return wmlUseCollectDao.queryWmlUseCollectList(item);
	}

	@Override
	public WmlUseCollect queryWmlUseCollect(WmlUseCollect item) {
		
		List<WmlUseCollect> WmlUseCollectList =wmlUseCollectDao.queryWmlUseCollectList(item);
		if(WmlUseCollectList.size()>0){
			return WmlUseCollectList.get(0);
		}
		return null;
	}

	@Transactional
	public String addWmlUseCollect(WmlUseCollect item) {
		String message=null;
		try{
			item.setIsDel(Constant.DELETE);
			wmlUseCollectDao.save(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

	@Transactional
	public String updateWmlUseCollect(WmlUseCollect item) {
		String message=null;
		try{
			wmlUseCollectDao.update(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

}
