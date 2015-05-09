package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlProductDao;
import com.pojo.WmlProduct;
import com.service.IWmlProductService;
import com.tool.Constant;




public class WmlProductServiceImpl implements IWmlProductService {

	private IWmlProductDao wmlProductDao;
	
	public void setWmlProductDao(IWmlProductDao wmlProductDao) {
		this.wmlProductDao = wmlProductDao;
	}

	@Override
	public List<WmlProduct> queryWmlProductList(WmlProduct item) {
		// TODO Auto-generated method stub
		return wmlProductDao.queryWmlProductList(item);
	}

	@Override
	public WmlProduct queryWmlProduct(WmlProduct item) {
		List<WmlProduct> WmlProductList=wmlProductDao.queryWmlProductList(item);
		if(WmlProductList.size()>0){
			return WmlProductList.get(0);
		}else{
			return null;
		}

	}


	@Transactional
	public String addWmlProduct(WmlProduct item) {
		String message=null;
		try{
			
			wmlProductDao.save(item);
			message=Constant.SUCCESS;
		}catch(Exception e){
			message =Constant.FAILURE;
			e.printStackTrace();
		}
		return message;
	}

	@Transactional
	public String updateWmlProduct(WmlProduct item) {
		String message=null;
		try{
			wmlProductDao.update(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}


	public List<WmlProduct> queryPageWmlProduct(WmlProduct item, int startRowNum,
			int pageSize) {
		return wmlProductDao.queryPageWmlProduct(item, startRowNum, pageSize);
	}
	
	public int getProductCount(WmlProduct item){
		return wmlProductDao.getProductCount(item);
	}
}
