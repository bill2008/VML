package com.dao;

import java.util.List;

import com.pojo.Wmlsms;

public interface IWmlsmsDao extends IObjectDAO {
	
	public List<Wmlsms> queryWmlsmsList(Wmlsms item);

}
