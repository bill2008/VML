package com.dao;

import java.util.List;

import com.pojo.WmlUseDownload;

public interface IWmlUseDownloadDao extends IObjectDAO {
	
	public List<WmlUseDownload> queryWmlUseDownloadList(WmlUseDownload item);
	

}
