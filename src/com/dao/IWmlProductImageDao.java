package com.dao;

import java.util.List;

import com.pojo.WmlProductImage;

public interface IWmlProductImageDao extends IObjectDAO {
	
	public List<WmlProductImage> queryWmlProductImageList(WmlProductImage item);

}
