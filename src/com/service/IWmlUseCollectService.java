package com.service;

import java.util.List;

import com.pojo.WmlUseCollect;

public interface IWmlUseCollectService  {
	
	public List<WmlUseCollect> queryWmlUseCollectList(WmlUseCollect item);
	
	public WmlUseCollect queryWmlUseCollect(WmlUseCollect item);
	
	public String addWmlUseCollect(WmlUseCollect item);
	
	public String updateWmlUseCollect(WmlUseCollect item);


}
