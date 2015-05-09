package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlProductImageDao;
import com.pojo.WmlProductImage;
import com.service.IWmlProductImageService;
import com.tool.Constant;


public class WmlProductImageServiceImpl implements IWmlProductImageService {

	private IWmlProductImageDao wmlProductImageDao;
	
	public void setWmlProductImageDao(IWmlProductImageDao wmlProductImageDao) {
		this.wmlProductImageDao = wmlProductImageDao;
	}

	@Override
	public List<WmlProductImage> queryWmlProductImageList(WmlProductImage item) {
		// TODO Auto-generated method stub
		try{
		return wmlProductImageDao.queryWmlProductImageList(item);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public WmlProductImage queryWmlProductImage(WmlProductImage item) {
		try{
		List<WmlProductImage> WmlProductImageList=wmlProductImageDao.queryWmlProductImageList(item);
				if(WmlProductImageList.size()>0){
					return WmlProductImageList.get(0);
				}else{
					return null;
				}
		}catch(Exception e ){
			e.printStackTrace();
			return null;
		}
	}

	
	@Transactional
	public String addWmlProductImage(WmlProductImage item) {
		String message=null;
		try{
			item.setIsDel(Constant.DELETE);
			wmlProductImageDao.save(item);
			message=Constant.SUCCESS;
		}catch(Exception e){
			message =Constant.FAILURE;
			e.printStackTrace();
		}
		return message;
	}

	@Transactional
	public String updateWmlProductImage(WmlProductImage item) {
		String message=null;
		try{
			wmlProductImageDao.update(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

}
