package com.dao;

import java.util.List;

import com.pojo.WmlUsePraise;

public interface IWmlUsePraiseDao extends IObjectDAO {
	
	public List<WmlUsePraise> queryWmlUsePraiseList(WmlUsePraise item);
	

}
