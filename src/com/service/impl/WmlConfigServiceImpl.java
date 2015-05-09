package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlConfigDao;
import com.pojo.WmlConfig;
import com.service.IWmlConfigService;
import com.tool.Constant;



public class WmlConfigServiceImpl implements IWmlConfigService {

	private IWmlConfigDao wmlConfigDao;
	
	public void setWmlConfigDao(IWmlConfigDao wmlConfigDao) {
		this.wmlConfigDao = wmlConfigDao;
	}

	@Override
	public List<WmlConfig> queryWmlConfigList(WmlConfig item) {
		
		return wmlConfigDao.queryWmlConfigList(item);
	}

	@Override
	public WmlConfig queryWmlConfig(WmlConfig item) {
		List<WmlConfig> WmlConfigList=wmlConfigDao.queryWmlConfigList(item);
				if(WmlConfigList.size()>0){
					return WmlConfigList.get(0);
				}else{
					return null;
				}
	}

	@Transactional
	public boolean updateWmlConfig(WmlConfig item) {
		try{
			wmlConfigDao.update(item);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean addWmlConfig(WmlConfig item) {
		try{
			item.setIsDel(Constant.DELETE);
			wmlConfigDao.save(item);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
