package com.service;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.Wmlsms;

public interface IWmlsmsService {
	
	public List<Wmlsms> queryWmlsmsList(Wmlsms item);

	public Wmlsms queryWmlsms(Wmlsms item);
	
	public boolean updateWmlsms(GridSupport gridData);
	
	
}
