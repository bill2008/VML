package com.action;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.Wmlsms;
import com.service.IWmlsmsService;

public class WmlsmsAction extends BaseAction {

	private IWmlsmsService wmlsmsService;
	private Wmlsms wmlsmsUser;
	private List<Wmlsms> data;
	private String message;
	public Wmlsms getWmlsmsUser() {
		return wmlsmsUser;
	}
	public void setWmlsmsUser(Wmlsms wmlsmsUser) {
		this.wmlsmsUser = wmlsmsUser;
	}
	public List<Wmlsms> getData() {
		return data;
	}
	public void setData(List<Wmlsms> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	public void setWmlsmsService(IWmlsmsService wmlsmsService) {
		this.wmlsmsService = wmlsmsService;
	}
	public String queryWmlsmsUser() throws Exception{
		data=wmlsmsService.queryWmlsmsList(wmlsmsUser);
		return "success";
	}
	
	public String updateWmlsmsUser() throws Exception{
		GridSupport gridData = new GridSupport(this._gt_json);
		if(wmlsmsService.updateWmlsms(gridData)){
			return "success";
		}else{
			return "fail";
		}
	}
}
