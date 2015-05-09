package com.dao;

import java.util.List;

import com.pojo.WmlAdvertisement;

public interface IWmlAdvertisementDao extends IObjectDAO{

	public List<WmlAdvertisement> queryWmlAdvertisementList(WmlAdvertisement item);
}
