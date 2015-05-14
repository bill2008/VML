package com.action;

import java.util.List;

import com.pojo.WmlMenu;
import com.service.IWmlMenuService;
import com.tool.BuildTreeUtil;
import com.tool.CommTreeInfo;

public class WmlMenuAction extends BaseAction {
	
	private List<CommTreeInfo> menuTree;
	private List<WmlMenu> data;
	private WmlMenu menu;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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
	
	public void addMenu() throws Exception{
		if(wmlMenuService.addMenu(menu)){
			message= "optsuccess";
		}else{
			message= "fail";
		}
		menu=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	public void updateMenu() throws Exception{
		
		WmlMenu item=new WmlMenu();
		item.setMenuId(menu.getMenuId());
		item.setMenuName(menu.getMenuName());
		item.setMenuLink(menu.getMenuLink());
		item.setMenuAid(menu.getMenuAid());
		item.setMenuNo(menu.getMenuNo());
		if(wmlMenuService.updateMenu(item)){
			message= "optsuccess";
		}else{
			message= "fail";
		}
		
		menu=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);		
		
	}

	
}
