package com.dao;

import java.util.List;

import com.pojo.WmlBrand;

public interface IWmlBrandDao extends IObjectDAO {

	public List<WmlBrand> queryWmlBrandList(WmlBrand item);
	
}
