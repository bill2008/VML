package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlBrandDao;
import com.pojo.WmlBrand;
import com.service.IWmlBrandService;
import com.tool.Constant;
import com.tool.TimeUtil;

public class WmlBrandServiceImpl implements IWmlBrandService {

	private IWmlBrandDao wmlBrandDao;

	public void setWmlBrandDao(IWmlBrandDao wmlBrandDao) {
		this.wmlBrandDao = wmlBrandDao;
	}

	@Override
	public List<WmlBrand> queryWmlBrandList(WmlBrand item) {
		// TODO Auto-generated method stub
		return wmlBrandDao.queryWmlBrandList(item);
	}

	@Override
	public WmlBrand queryWmlBrand(WmlBrand item) {
		List<WmlBrand> WmlBrandList = wmlBrandDao.queryWmlBrandList(item);
		if (WmlBrandList.size() > 0) {
			return WmlBrandList.get(0);
		} else {
			return null;
		}

	}

	@Transactional
	public boolean updateWmlBrand(WmlBrand item) {
		try {
			wmlBrandDao.update(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean addWmlBrand(WmlBrand item) {
		try {
			item.setIsDel(Constant.DELETE);
			item.setCreateDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			wmlBrandDao.save(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
