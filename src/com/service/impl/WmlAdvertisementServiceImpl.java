package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlAdvertisementDao;
import com.pojo.WmlAdvertisement;
import com.service.IWmlAdvertisementService;
import com.tool.Constant;


public class WmlAdvertisementServiceImpl implements IWmlAdvertisementService {

	private IWmlAdvertisementDao wmlAdvertisementDao;

	public void setWmlAdvertisementDao(IWmlAdvertisementDao wmlAdvertisementDao) {
		this.wmlAdvertisementDao = wmlAdvertisementDao;
	}

	@Override
	public List<WmlAdvertisement> queryWmlAdvertisementList(
			WmlAdvertisement item) {

		return wmlAdvertisementDao.queryWmlAdvertisementList(item);
	}

	@Override
	public WmlAdvertisement queryWmlAdvertisement(WmlAdvertisement item) {
		List<WmlAdvertisement> WmlAdvertisementList = wmlAdvertisementDao
				.queryWmlAdvertisementList(item);
		if (WmlAdvertisementList.size() > 0) {
			return WmlAdvertisementList.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public boolean updateWmlAdvertisement(WmlAdvertisement item) {
		try {
			wmlAdvertisementDao.update(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean addWmlAdvertisement(WmlAdvertisement item) {
		try {
			item.setIsDel(Constant.DELETE);
			wmlAdvertisementDao.save(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
