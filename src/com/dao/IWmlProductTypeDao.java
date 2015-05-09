package com.dao;

import java.util.List;

import com.pojo.WmlProductType;

public interface IWmlProductTypeDao extends IObjectDAO {
	
	public List<WmlProductType> queryWmlProductTypeList(WmlProductType item);

}
