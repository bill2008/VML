package com.action;

import java.util.List;

import com.pojo.WmlBrand;
import com.service.IWmlBrandService;

public class WmlBrandAction extends BaseAction {

	private IWmlBrandService wmlBrandService;
	private WmlBrand wmlBrand;
	private List<WmlBrand> data;
	private String message;
	public WmlBrand getWmlBrand() {
		return wmlBrand;
	}
	public void setWmlBrand(WmlBrand wmlBrand) {
		this.wmlBrand = wmlBrand;
	}
	public List<WmlBrand> getData() {
		return data;
	}
	public void setData(List<WmlBrand> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlBrandService(IWmlBrandService wmlBrandService) {
		this.wmlBrandService = wmlBrandService;
	}
	
	public String queryWmlBrand() throws Exception {
		data=wmlBrandService.queryWmlBrandList(wmlBrand);
		wmlBrand=null;
		return "success";
	}
	
	public void addWmlBrand()throws Exception{
		if(wmlBrandService.addWmlBrand(wmlBrand)){
			message= "optsuccess";
		}else{
			message= "fail";
		}	
		wmlBrand=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	public void updateWmlBrand() throws Exception {
	
		if(wmlBrandService.updateWmlBrand(wmlBrand)){
			message= "optsuccess";
		}else{
			message= "fail";
		}	
		wmlBrand=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
}
