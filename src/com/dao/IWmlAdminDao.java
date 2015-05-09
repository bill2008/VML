package com.dao;

import java.util.List;

import com.pojo.WmlAdmin;

public interface IWmlAdminDao extends IObjectDAO {
	
	public List<WmlAdmin> queryWmlAdminList(WmlAdmin item);

}
