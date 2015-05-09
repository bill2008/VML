package com.dao;

import java.util.List;

import com.pojo.WmlMenu;

public interface IWmlMenuDAO extends IObjectDAO{
	
	/**
	 * 获取所有菜单信息
	 * @return
	 */
	public List<WmlMenu> AllMenu();
	
	public List<WmlMenu> queryMenu(WmlMenu item);
	

}
