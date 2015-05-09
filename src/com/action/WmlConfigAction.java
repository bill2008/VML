package com.action;

import java.util.List;

import com.pojo.WmlConfig;
import com.service.IWmlConfigService;

public class WmlConfigAction extends BaseAction {

	private IWmlConfigService wmlConfigService;
	private WmlConfig wmlConfig;
	private List<WmlConfig> data;
	private String message;
	public WmlConfig getWmlConfig() {
		return wmlConfig;
	}
	public void setWmlConfig(WmlConfig wmlConfig) {
		this.wmlConfig = wmlConfig;
	}
	public List<WmlConfig> getData() {
		return data;
	}
	public void setData(List<WmlConfig> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlConfigService(IWmlConfigService wmlConfigService) {
		this.wmlConfigService = wmlConfigService;
	}
	
	public String queryWmlConfig()throws Exception{

		data=wmlConfigService.queryWmlConfigList(wmlConfig);
		wmlConfig=null;
		return "success";
	}
	
	public void addWmlConfig()throws Exception{
		if(wmlConfigService.addWmlConfig(wmlConfig)){
			message= "optsuccess";
		}else{
			message= "fail";
		}	
		wmlConfig=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	public void updateWmlConfig()throws Exception{
		if(wmlConfigService.updateWmlConfig(wmlConfig)){
			message= "optsuccess";
		}else{
			message= "fail";
		}	
		wmlConfig=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
}
