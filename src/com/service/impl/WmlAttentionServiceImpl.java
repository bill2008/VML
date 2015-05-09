package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlAttentionDao;
import com.grid.tool.GridSupport;
import com.pojo.WmlAttention;
import com.service.IWmlAttentionService;
import com.tool.Constant;


public class WmlAttentionServiceImpl implements IWmlAttentionService {

	private IWmlAttentionDao wmlAttentionDao;
	
	public void setWmlAttentionDao(IWmlAttentionDao wmlAttentionDao) {
		this.wmlAttentionDao = wmlAttentionDao;
	}

	@Override
	public List<WmlAttention> queryWmlAttentionList(WmlAttention item) {
		// TODO Auto-generated method stub
		return wmlAttentionDao.queryWmlAttentionList(item);
	}

	@Override
	public WmlAttention queryWmlAttention(WmlAttention item) {
		List<WmlAttention> WmlAttentionList=wmlAttentionDao.queryWmlAttentionList(item);
		if(WmlAttentionList.size()>0){
			return WmlAttentionList.get(0);
		}else{
			return null;
		}
	}

	@Transactional
	public boolean updateWmlAttention(GridSupport gridData) {
		try{
			List<WmlAttention> addRecords=gridData.getParamRecords(GridSupport.RECORD_INSERT, WmlAttention.class);
			List<WmlAttention> updateRecords=gridData.getParamRecords(GridSupport.RECORD_UPDATE, WmlAttention.class);
			if(addRecords != null && addRecords.size() > 0){
				for(WmlAttention item:addRecords){
					item.setIsDel(Constant.DELETE);
					wmlAttentionDao.save(item);
				}
			}
			if(updateRecords != null && updateRecords.size() > 0){
				for(WmlAttention item:updateRecords){
					wmlAttentionDao.saveOrUpdate(item);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


}
