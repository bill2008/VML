package com.service;

import java.util.List;

import com.pojo.WmlAdvertisement;

public interface IWmlAdvertisementService {

	public List<WmlAdvertisement> queryWmlAdvertisementList(WmlAdvertisement item);
	
	public WmlAdvertisement queryWmlAdvertisement(WmlAdvertisement item);
	
	public boolean updateWmlAdvertisement(WmlAdvertisement item);
	
	public boolean addWmlAdvertisement(WmlAdvertisement item);
	
}
