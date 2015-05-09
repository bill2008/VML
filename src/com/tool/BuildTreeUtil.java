package com.tool;

import java.util.ArrayList;
import java.util.List;

import com.pojo.WmlMenu;

public class BuildTreeUtil {
	
	/**
	 * 转换菜单数据为通用zTree格式
	 * @param menuList
	 * @return
	 */
	public static List<CommTreeInfo> getTree(List<WmlMenu> menuList){
		List<CommTreeInfo> treeList =new ArrayList<CommTreeInfo>();
		for(int i=0;i<menuList.size();i++){
			CommTreeInfo tree = new CommTreeInfo();
			tree.setId(String.valueOf(menuList.get(i).getMenuId()));
			tree.setpId(String.valueOf(menuList.get(i).getMenuAid()));
			tree.setName(menuList.get(i).getMenuName());
			if(menuList.get(i).getMenuId()==1){
				tree.setOpen(true);
			}
			tree.setTarget("rightFrame");
			tree.setUrl(menuList.get(i).getMenuLink());
			if(menuList.get(i).getMenuAid()==0){
            	tree.setParent(true);
            }else{
            	tree.setParent(false);
            }
			treeList.add(tree);
		}
		
		
		return treeList;
	}
}
