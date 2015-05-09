package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlsmsDao;
import com.grid.tool.GridSupport;
import com.pojo.Wmlsms;
import com.service.IWmlsmsService;
import com.tool.Constant;


public class WmlsmsServiceImpl implements IWmlsmsService {

	private IWmlsmsDao wmlsmsDao;

	public void setWmlsmsDao(IWmlsmsDao wmlsmsDao) {
		this.wmlsmsDao = wmlsmsDao;
	}

	@Override
	public List<Wmlsms> queryWmlsmsList(Wmlsms item) {
		// TODO Auto-generated method stub
		return wmlsmsDao.queryWmlsmsList(item);
	}

	@Override
	public Wmlsms queryWmlsms(Wmlsms item) {
		List<Wmlsms> WmlsmsUserList=wmlsmsDao.queryWmlsmsList(item);
		if(WmlsmsUserList.size()>0){
			return WmlsmsUserList.get(0);
		}else{
			return null;
		}
	}

	@Transactional
	public boolean updateWmlsms(GridSupport gridData) {
		try{
			List<Wmlsms> addRecords=gridData.getParamRecords(GridSupport.RECORD_INSERT, Wmlsms.class);
			List<Wmlsms> updateRecords=gridData.getParamRecords(GridSupport.RECORD_UPDATE, Wmlsms.class);
			if(addRecords != null && addRecords.size() > 0){
				for(Wmlsms item:addRecords){
					item.setIsDel(Constant.DELETE);
					wmlsmsDao.save(item);
				}
			}
			if(updateRecords != null && updateRecords.size() > 0){
				for(Wmlsms item:updateRecords){
					wmlsmsDao.saveOrUpdate(item);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
