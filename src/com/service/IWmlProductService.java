package com.service;

import java.util.List;

import com.pojo.WmlProduct;

public interface IWmlProductService {

	public List<WmlProduct> queryWmlProductList(WmlProduct item);
	
	public WmlProduct queryWmlProduct(WmlProduct item);
	
	public List<WmlProduct> queryPageWmlProduct(WmlProduct item,int startRowNum,int pageSize);
	
	public int getProductCount(WmlProduct item);
	
	public String addWmlProduct(WmlProduct item);
	
	public String updateWmlProduct(WmlProduct item);
	
	
}
