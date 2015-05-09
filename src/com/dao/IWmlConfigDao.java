package com.dao;

import java.util.List;

import com.pojo.WmlConfig;

public interface IWmlConfigDao extends IObjectDAO {
	
	public List<WmlConfig> queryWmlConfigList(WmlConfig item);

}
