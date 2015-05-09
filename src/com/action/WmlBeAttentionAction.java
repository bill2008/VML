package com.action;

import java.util.List;

import com.pojo.WmlAttention;
import com.service.IWmlAttentionService;


/**
 * 被关注Action
 * @author Administrator
 *
 */
public class WmlBeAttentionAction extends BaseAction {
	
	private IWmlAttentionService wmlAttentionService;
	private WmlAttention wmlAttention;
	private List<WmlAttention> data;
	private String message;
	public WmlAttention getWmlAttention() {
		return wmlAttention;
	}
	public void setWmlAttention(WmlAttention wmlAttention) {
		this.wmlAttention = wmlAttention;
	}
	public List<WmlAttention> getData() {
		return data;
	}
	public void setData(List<WmlAttention> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlAttentionService(IWmlAttentionService wmlAttentionService) {
		this.wmlAttentionService = wmlAttentionService;
	}
	
	public String queryWmlAttentionList() throws Exception{
		data=wmlAttentionService.queryWmlAttentionList(wmlAttention);
		wmlAttention=null;
		return "success";
	}
	

	
 
}
