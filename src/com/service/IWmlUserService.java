package com.service;

import java.util.List;

import com.pojo.WmlUser;


public interface IWmlUserService {

	public List<WmlUser> queryWmlUserList(WmlUser item);
	
	public WmlUser queryWmlUser(WmlUser item);
	
	public String addWmlUser(WmlUser item);
	
	public String updateWmlUser(WmlUser item);
	
	public int getWmlUserCount(WmlUser item);
	
	public List<WmlUser> queryWmlUserPage(WmlUser item,int startRowNum,int pageSize);
	
}
