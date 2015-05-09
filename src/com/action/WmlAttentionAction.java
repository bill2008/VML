package com.action;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.WmlAttention;
import com.service.IWmlAttentionService;

/**
 * 关注列表
 * @author Administrator
 *
 */
public class WmlAttentionAction extends BaseAction {
	
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
	
	public String queryWmlAttention() throws Exception{
		data=wmlAttentionService.queryWmlAttentionList(wmlAttention);
		wmlAttention=null;
		return "success";
	}	
	
	public String updateWmlAttention() throws Exception{
		GridSupport gridData = new GridSupport(this._gt_json);
		if(wmlAttentionService.updateWmlAttention(gridData)){
			return "success";
		}else{
			return "fail";
		}
	}
	
 
}
