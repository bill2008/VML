package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlUseDownloadDao;
import com.pojo.WmlUseDownload;
import com.service.IWmlUseDownloadService;
import com.tool.Constant;

public class WmlUseDownloadServiceImpl implements IWmlUseDownloadService {

	private IWmlUseDownloadDao wmlUseDownloadDao;
	
	public void setWmlUseDownloadDao(IWmlUseDownloadDao wmlUseDownloadDao) {
		this.wmlUseDownloadDao = wmlUseDownloadDao;
	}

	@Override
	public List<WmlUseDownload> queryWmlUseDownloadList(WmlUseDownload item) {
		// TODO Auto-generated method stub
		return wmlUseDownloadDao.queryWmlUseDownloadList(item);
	}

	@Override
	public WmlUseDownload queryWmlUseDownload(WmlUseDownload item) {
	
		List<WmlUseDownload> WmlUseDownloadList =wmlUseDownloadDao.queryWmlUseDownloadList(item);
		if(WmlUseDownloadList.size()>0){
			return WmlUseDownloadList.get(0);
		}
		return null;
	}

	@Transactional
	public String addWmlUseDownload(WmlUseDownload item) {
		String message=null;
		try{
			item.setIsDel(Constant.DELETE);
			wmlUseDownloadDao.save(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}


	@Transactional
	public String updateWmlUseDownload(WmlUseDownload item) {
		String message=null;
		try{
			wmlUseDownloadDao.update(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

}
