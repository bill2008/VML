package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlProductTypeDao;
import com.grid.tool.GridSupport;
import com.pojo.WmlProductType;
import com.service.IWmlProductTypeService;
import com.tool.Constant;


public class WmlProductTypeServiceImpl implements IWmlProductTypeService {

	private IWmlProductTypeDao wmlProductTypeDao;
	
	public void setWmlProductTypeDao(IWmlProductTypeDao wmlProductTypeDao) {
		this.wmlProductTypeDao = wmlProductTypeDao;
	}

	@Override
	public List<WmlProductType> queryWmlProductTypeList(WmlProductType item) {
		// TODO Auto-generated method stub
		return wmlProductTypeDao.queryWmlProductTypeList(item);
	}

	@Override
	public WmlProductType queryWmlProductType(WmlProductType item) {
		List<WmlProductType> WmlProductTypeList=wmlProductTypeDao.queryWmlProductTypeList(item);
		if(WmlProductTypeList.size()>0){
			return WmlProductTypeList.get(0);
		}else{
			return null;
		}
		
	}

	@Transactional
	public boolean updateWmlProductType(GridSupport gridData) {
		try{
			List<WmlProductType> addRecords=gridData.getParamRecords(GridSupport.RECORD_INSERT, WmlProductType.class);
			List<WmlProductType> updateRecords=gridData.getParamRecords(GridSupport.RECORD_UPDATE, WmlProductType.class);
			if(addRecords != null && addRecords.size() > 0){
				for(WmlProductType item:addRecords){
					item.setIsDel(Constant.DELETE);
					wmlProductTypeDao.save(item);
				}
			}
			if(updateRecords != null && updateRecords.size() > 0){
				for(WmlProductType item:updateRecords){
					wmlProductTypeDao.saveOrUpdate(item);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
