package com.service;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.WmlProductType;

public interface IWmlProductTypeService {

	public List<WmlProductType> queryWmlProductTypeList(WmlProductType item);

	public WmlProductType queryWmlProductType(WmlProductType item);
	
	public boolean updateWmlProductType(GridSupport gridData);
	
}
