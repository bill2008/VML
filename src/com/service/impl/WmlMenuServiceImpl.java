package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlMenuDAO;
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
	public boolean updateMenu(WmlMenu item) {
		try{
			wmlMenuDao.saveOrUpdate(item);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean addMenu(WmlMenu item) {
		try{
			wmlMenuDao.save(item);
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
