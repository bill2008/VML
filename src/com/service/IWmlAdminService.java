package com.service;

import java.util.List;

import com.pojo.WmlAdmin;


public interface IWmlAdminService {

	public List<WmlAdmin> queryWmlAdminList(WmlAdmin item);
	
	public WmlAdmin queryWmlAdmin(WmlAdmin item);
	
	public boolean updateWmlAdmin(WmlAdmin item);
	
	public boolean addWmlAdmin(WmlAdmin item);
	
}
