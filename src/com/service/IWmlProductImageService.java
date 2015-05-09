package com.service;

import java.util.List;

import com.pojo.WmlProductImage;


public interface IWmlProductImageService {

	public List<WmlProductImage> queryWmlProductImageList(WmlProductImage item);

	public WmlProductImage queryWmlProductImage(WmlProductImage item);
	
	public String addWmlProductImage(WmlProductImage item);
	
	public String updateWmlProductImage(WmlProductImage item);
	
}
