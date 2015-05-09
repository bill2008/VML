package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlMenuDAO;
import com.grid.tool.GridSupport;
import com.pojo.WmlMenu;
import com.service.IWmlMenuService;

public class WmlMenuServiceImpl implements IWmlMenuService {
	
	private IWmlMenuDAO wmlMenuDao;
	

	public void setWmlMenuDao(IWmlMenuDAO wmlMenuDao) {
		this.wmlMenuDao = wmlMenuDao;
	}


	@Override
	public List<WmlMenu> AllMenu() {
		// TODO Auto-generated method stub
		return wmlMenuDao.AllMenu();
	}


	@Transactional
	public boolean updateMenu(GridSupport gridData) {
		try{
			List<WmlMenu> addRecords=gridData.getParamRecords(GridSupport.RECORD_INSERT, WmlMenu.class);
			List<WmlMenu> updateRecords=gridData.getParamRecords(GridSupport.RECORD_UPDATE, WmlMenu.class);
			List<WmlMenu> deleteRecords=gridData.getParamRecords(GridSupport.RECORD_DELETE, WmlMenu.class);
			if(addRecords != null && addRecords.size() > 0){
				for(WmlMenu item:addRecords){
					wmlMenuDao.save(item);
				}
			}
			if(updateRecords != null && updateRecords.size() > 0){
				for(WmlMenu item:updateRecords){
					wmlMenuDao.saveOrUpdate(item);
				}
			}
			if(deleteRecords != null && deleteRecords.size() > 0){
				for(WmlMenu item:deleteRecords){
					wmlMenuDao.delete(item);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<WmlMenu> queryMenu(WmlMenu item) {
		// TODO Auto-generated method stub
		return wmlMenuDao.queryMenu(item);
	}

}
