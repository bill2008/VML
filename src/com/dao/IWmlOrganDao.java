package com.dao;

import java.util.List;

import com.pojo.WmlOrgan;

public interface IWmlOrganDao extends IObjectDAO {
	
	public List<WmlOrgan> queryWmlOrganList(WmlOrgan item);

}
