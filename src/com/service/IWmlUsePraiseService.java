package com.service;

import java.util.List;

import com.pojo.WmlUsePraise;

public interface IWmlUsePraiseService {
	
	public List<WmlUsePraise> queryWmlUsePraiseList(WmlUsePraise item);
	
	public WmlUsePraise queryWmlUsePraise(WmlUsePraise item);
	
	public String addWmlUsePraise(WmlUsePraise item);
	
	public String updateWmlUsePraise(WmlUsePraise item);

}
