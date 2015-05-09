package com.dao;

import java.util.List;

import com.pojo.WmlProduct;

public interface IWmlProductDao extends IObjectDAO {
	
	public List<WmlProduct> queryWmlProductList(WmlProduct item);
	
	public int getProductCount(WmlProduct item);
	
	public List<WmlProduct> queryPageWmlProduct(WmlProduct item,int startRowNum,int pageSize);

}
