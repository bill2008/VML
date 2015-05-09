package com.service;

import java.util.List;

import com.pojo.WmlOrgan;

public interface IWmlOrganService {

	public List<WmlOrgan> queryWmlOrganList(WmlOrgan item);
	
	public WmlOrgan queryWmlOrgan(WmlOrgan item);
	
	public boolean updateWmlOrgan(WmlOrgan item);
	
	public boolean addWmlOrgan(WmlOrgan item);

}
