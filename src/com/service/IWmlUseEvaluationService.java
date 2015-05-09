package com.service;

import java.util.List;

import com.pojo.WmlUseEvaluation;

public interface IWmlUseEvaluationService {
	
	public List<WmlUseEvaluation> queryWmlUseEvaluationList(WmlUseEvaluation item);
	
	public WmlUseEvaluation queryWmlUseEvaluation(WmlUseEvaluation item);
	
	public String addWmlUseEvaluation(WmlUseEvaluation item);
	
	public String updateWmlUseEvaluation(WmlUseEvaluation item);
	
	
}
