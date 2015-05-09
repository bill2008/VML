package com.service;

import java.util.List;

import com.pojo.WmlBrand;

public interface IWmlBrandService {

	public List<WmlBrand> queryWmlBrandList(WmlBrand item);
	
	public WmlBrand queryWmlBrand(WmlBrand item);
	
	public boolean updateWmlBrand(WmlBrand item);
	
	public boolean addWmlBrand(WmlBrand item);
	

	
}
