package com.service;

import java.util.List;

import com.pojo.WmlConfig;

public interface IWmlConfigService {

	public List<WmlConfig> queryWmlConfigList(WmlConfig item);
	
	public WmlConfig queryWmlConfig(WmlConfig item);
	
	public boolean updateWmlConfig(WmlConfig item);
	
	public boolean addWmlConfig(WmlConfig item);
	
}
