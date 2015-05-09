package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlInstallationlogDao;
import com.grid.tool.GridSupport;
import com.pojo.ClinetInsertType;
import com.pojo.WmlInstallationlog;
import com.service.IWmlInstallationlogService;
import com.tool.Constant;


public class WmlInstallationlogServiceImpl implements
		IWmlInstallationlogService {

	private IWmlInstallationlogDao wmlInstallationlogDao;
	
	public void setWmlInstallationlogDao(
			IWmlInstallationlogDao wmlInstallationlogDao) {
		this.wmlInstallationlogDao = wmlInstallationlogDao;
	}

	@Override
	public List<WmlInstallationlog> queryWmlInstallationlogList(
			WmlInstallationlog item) {
		// TODO Auto-generated method stub
		return wmlInstallationlogDao.queryWmlInstallationlogList(item);
	}

	@Override
	public WmlInstallationlog queryWmlInstallationlog(WmlInstallationlog item) {
		List<WmlInstallationlog> WmlInstallationlogList=wmlInstallationlogDao.queryWmlInstallationlogList(item);
		if(WmlInstallationlogList.size()>0){
			return WmlInstallationlogList.get(0);
		}else{
		return null;
		}
	}

	@Transactional
	public boolean updateWmlInstallationlog(GridSupport gridData) {
		try{
			List<WmlInstallationlog> addRecords=gridData.getParamRecords(GridSupport.RECORD_INSERT, WmlInstallationlog.class);
			List<WmlInstallationlog> updateRecords=gridData.getParamRecords(GridSupport.RECORD_UPDATE, WmlInstallationlog.class);
			if(addRecords != null && addRecords.size() > 0){
				for(WmlInstallationlog item:addRecords){
					item.setIsDel(Constant.DELETE);
					wmlInstallationlogDao.save(item);
				}
			}
			if(updateRecords != null && updateRecords.size() > 0){
				for(WmlInstallationlog item:updateRecords){
					wmlInstallationlogDao.saveOrUpdate(item);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ClinetInsertType> queryInstallClientType(String clientType) {
		// TODO Auto-generated method stub
		return wmlInstallationlogDao.queryInstallClientType(clientType);
	}

}
