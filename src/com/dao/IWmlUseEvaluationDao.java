package com.dao;

import java.util.List;

import com.pojo.WmlUseEvaluation;

public interface IWmlUseEvaluationDao extends IObjectDAO {
	
	public List<WmlUseEvaluation> queryWmlUseEvaluationList(WmlUseEvaluation item);
	
	
}
