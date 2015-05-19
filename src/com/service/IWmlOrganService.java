package com.service;

import java.util.List;

import com.pojo.WmlOrgan;

public interface IWmlOrganService {

	public List<WmlOrgan> queryWmlOrganList(WmlOrgan item);
	
	public WmlOrgan queryWmlOrgan(WmlOrgan item);
	
	public String updateWmlOrgan(WmlOrgan item);
	
	public String updateWmlOrganPermissions(WmlOrgan item);
	
	public boolean addWmlOrgan(WmlOrgan item);

}
