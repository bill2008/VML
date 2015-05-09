package com.service;

import java.util.List;

import com.pojo.WmlUseDownload;

public interface IWmlUseDownloadService  {
	
	public List<WmlUseDownload> queryWmlUseDownloadList(WmlUseDownload item);
	
	public WmlUseDownload queryWmlUseDownload(WmlUseDownload item);
	
	public String addWmlUseDownload(WmlUseDownload item);
	
	public String updateWmlUseDownload(WmlUseDownload item);
	

}
