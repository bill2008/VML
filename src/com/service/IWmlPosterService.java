package com.service;

import java.util.List;

import com.pojo.WmlPoster;

public interface IWmlPosterService {

	public List<WmlPoster> queryWmlPosterList(WmlPoster item);
	
	public WmlPoster queryWmlPoster(WmlPoster item);
	
	public boolean updateWmlPoster(WmlPoster item);
	
	public boolean addWmlPoster(WmlPoster item);
	
}
