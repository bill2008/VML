package com.dao;

import java.util.List;

import com.pojo.WmlUseCollect;

public interface IWmlUseCollectDao extends IObjectDAO {
	
	public List<WmlUseCollect> queryWmlUseCollectList(WmlUseCollect item);
	


}
