package com.action;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.WmlMenu;
import com.service.IWmlMenuService;
import com.tool.BuildTreeUtil;
import com.tool.CommTreeInfo;

public class WmlMenuAction extends BaseAction {
	
	private List<CommTreeInfo> menuTree;
	private List<WmlMenu> data;
	private WmlMenu menu;
	
	public WmlMenu getMenu() {
		return menu;
	}

	public void setMenu(WmlMenu menu) {
		this.menu = menu;
	}

	private IWmlMenuService wmlMenuService;

	
	public List<WmlMenu> getData() {
		return data;
	}

	public void setData(List<WmlMenu> data) {
		this.data = data;
	}

	public List<CommTreeInfo> getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(List<CommTreeInfo> menuTree) {
		this.menuTree = menuTree;
	}

	public void setWmlMenuService(IWmlMenuService wmlMenuService) {
		this.wmlMenuService = wmlMenuService;
	}
	
	//查询所有菜单信息
	public String AllMenu() throws Exception{
	
		List<WmlMenu> menuTreeList =wmlMenuService.AllMenu();
		
		menuTree=BuildTreeUtil.getTree(menuTreeList);
		
		return "success";
	} 
	
	public String queryMenu() throws Exception{
		data=wmlMenuService.queryMenu(menu);
		return "success";
	} 
	
	public String updateMenu() throws Exception{
		GridSupport gridData = new GridSupport(this._gt_json);
		if(wmlMenuService.updateMenu(gridData)){
			return "success";
		}else{
			return "fail";
		}	
	}

	
}
