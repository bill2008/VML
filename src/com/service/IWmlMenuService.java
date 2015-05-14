package com.service;

import java.util.List;

import com.pojo.WmlMenu;

public interface IWmlMenuService {
	
	public List<WmlMenu> AllMenu();
	
	public boolean updateMenu(WmlMenu item);
	
	public List<WmlMenu> queryMenu(WmlMenu item);
	
	public boolean addMenu(WmlMenu item);
}
