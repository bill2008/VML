package com.service;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.WmlMenu;

public interface IWmlMenuService {
	
	public List<WmlMenu> AllMenu();
	
	public boolean updateMenu(GridSupport gridData);
	
	
	public List<WmlMenu> queryMenu(WmlMenu item);
}
