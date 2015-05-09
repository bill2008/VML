package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlPosterDao;
import com.pojo.WmlPoster;
import com.service.IWmlPosterService;
import com.tool.Constant;


public class WmlPosterServiceImpl implements IWmlPosterService {

	private IWmlPosterDao wmlPosterDao;
	
	public void setWmlPosterDao(IWmlPosterDao wmlPosterDao) {
		this.wmlPosterDao = wmlPosterDao;
	}

	@Override
	public List<WmlPoster> queryWmlPosterList(WmlPoster item) {
		// TODO Auto-generated method stub
		return wmlPosterDao.queryWmlPosterList(item);
	}

	@Override
	public WmlPoster queryWmlPoster(WmlPoster item) {
		List<WmlPoster> WmlPosterList=wmlPosterDao.queryWmlPosterList(item);
		if(WmlPosterList.size()>0){
			return WmlPosterList.get(0);
		}else{
			return null;
		}
	}

	@Transactional
	public boolean updateWmlPoster(WmlPoster item) {
		try{
			wmlPosterDao.update(item);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean addWmlPoster(WmlPoster item) {
		try{
			item.setIsDel(Constant.DELETE);
			wmlPosterDao.save(item);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
