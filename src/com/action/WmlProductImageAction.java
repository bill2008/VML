package com.action;

import java.util.List;

import com.pojo.WmlProductImage;
import com.service.IWmlProductImageService;
import com.tool.Constant;

public class WmlProductImageAction extends BaseAction {

	private IWmlProductImageService wmlProductImageService;
	private WmlProductImage wmlProductImage;
	private List<WmlProductImage> data;
	private String message;
	public WmlProductImage getWmlProductImage() {
		return wmlProductImage;
	}
	public void setWmlProductImage(WmlProductImage wmlProductImage) {
		this.wmlProductImage = wmlProductImage;
	}
	public List<WmlProductImage> getData() {
		return data;
	}
	public void setData(List<WmlProductImage> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlProductImageService(
			IWmlProductImageService wmlProductImageService) {
		this.wmlProductImageService = wmlProductImageService;
	}
	
	public String queryWmlProductImage() throws Exception{
		data =wmlProductImageService.queryWmlProductImageList(wmlProductImage);
		return "success";
	}
	
	public void updateWmlProductImage() throws Exception{
		if(wmlProductImageService.updateWmlProductImage(wmlProductImage).equals(Constant.MSG_SUCCESS)){
			message= "optsuccess";
		}else{
			message= "fail";
		}	
		wmlProductImage=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
}
