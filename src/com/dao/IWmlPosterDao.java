package com.dao;

import java.util.List;

import com.pojo.WmlPoster;

public interface IWmlPosterDao extends IObjectDAO {
	
	public List<WmlPoster> queryWmlPosterList(WmlPoster item);

}
