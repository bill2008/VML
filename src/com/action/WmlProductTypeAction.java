package com.action;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.WmlProductType;
import com.service.IWmlProductTypeService;

public class WmlProductTypeAction extends BaseAction {

	private IWmlProductTypeService wmlProductTypeService;
	private WmlProductType wmlProductType;
	private List<WmlProductType> data;
	private String message;
	public WmlProductType getWmlProductType() {
		return wmlProductType;
	}
	public void setWmlProductType(WmlProductType wmlProductType) {
		this.wmlProductType = wmlProductType;
	}
	public List<WmlProductType> getData() {
		return data;
	}
	public void setData(List<WmlProductType> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlProductTypeService(
			IWmlProductTypeService wmlProductTypeService) {
		this.wmlProductTypeService = wmlProductTypeService;
	}
	
	public String queryWmlProductType() throws Exception{
		data=wmlProductTypeService.queryWmlProductTypeList(wmlProductType);
		return "success";
	}
	
	public String updateWmlProductType() throws Exception{
		GridSupport gridData = new GridSupport(this._gt_json);
		if(wmlProductTypeService.updateWmlProductType(gridData)){
			return "success";
		}else{
			return "fail";
		}
	}
}
