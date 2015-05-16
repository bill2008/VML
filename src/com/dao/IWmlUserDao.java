package com.dao;

import java.util.List;

import com.pojo.WmlUser;

public interface IWmlUserDao extends IObjectDAO {
	
	public List<WmlUser> queryWmlUserList(WmlUser item);
	
	public List<WmlUser> queryWmlUserPage(WmlUser item, int startRowNum,
			int pageSize);
	
	public int getWmlUserCount(WmlUser item);
	
	public int updateWmlUserPermissions(WmlUser item);

}
