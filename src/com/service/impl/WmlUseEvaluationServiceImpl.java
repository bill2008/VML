package com.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dao.IWmlUseEvaluationDao;
import com.pojo.WmlUseEvaluation;
import com.service.IWmlUseEvaluationService;
import com.tool.Constant;

public class WmlUseEvaluationServiceImpl implements IWmlUseEvaluationService {
	
	private IWmlUseEvaluationDao wmlUseEvaluationDao;
	
	public void setWmlUseEvaluationDao(IWmlUseEvaluationDao wmlUseEvaluationDao) {
		this.wmlUseEvaluationDao = wmlUseEvaluationDao;
	}

	@Override
	public List<WmlUseEvaluation> queryWmlUseEvaluationList(
			WmlUseEvaluation item) {
		// TODO Auto-generated method stub
		return wmlUseEvaluationDao.queryWmlUseEvaluationList(item);
	}

	@Override
	public WmlUseEvaluation queryWmlUseEvaluation(WmlUseEvaluation item) {
		List<WmlUseEvaluation> WmlUserEvaluationList =wmlUseEvaluationDao.queryWmlUseEvaluationList(item);
		if(WmlUserEvaluationList.size()>0){
			return WmlUserEvaluationList.get(0);
		}
		return null;
	}

	@Transactional
	public String addWmlUseEvaluation(WmlUseEvaluation item) {
		String message=null;
		try{
			item.setIsDel(Constant.DELETE);
			wmlUseEvaluationDao.save(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

	@Transactional
	public String updateWmlUseEvaluation(WmlUseEvaluation item) {
		String message=null;
		try{
			wmlUseEvaluationDao.update(item);
			message=Constant.MSG_SUCCESS;
		}catch(Exception e){
			message =Constant.MSG_FAILURE;
			e.printStackTrace();
		}
		return message;
	}

}
