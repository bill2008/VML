package com.action;

import java.util.List;

import com.pojo.WmlOrgan;
import com.pojo.WmlUser;
import com.service.IWmlOrganService;
import com.service.IWmlUserService;
import com.tool.Constant;

public class WmlOrganAction extends BaseAction {

	private IWmlOrganService wmlOrganService;
	private IWmlUserService wmlUserService;
	private WmlOrgan wmlOrgan;
	private List<WmlOrgan> data;
	private String message;
	public WmlOrgan getWmlOrgan() {
		return wmlOrgan;
	}
	public void setWmlOrgan(WmlOrgan wmlOrgan) {
		this.wmlOrgan = wmlOrgan;
	}
	public List<WmlOrgan> getData() {
		return data;
	}
	public void setData(List<WmlOrgan> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlOrganService(IWmlOrganService wmlOrganService) {
		this.wmlOrganService = wmlOrganService;
	}
	
	
	public void setWmlUserService(IWmlUserService wmlUserService) {
		this.wmlUserService = wmlUserService;
	}
	public String queryWmlOrgan() throws Exception{
		data=wmlOrganService.queryWmlOrganList(wmlOrgan);
		wmlOrgan=null;
		return "success";
	}
	public void updateWmlOrgan() throws Exception{
		WmlUser item=new WmlUser();
		item.setId(wmlOrgan.getUserId());
		item =  wmlUserService.queryWmlUser(item);
		if(item!=null){
			item.setOrgan(wmlOrgan.getId());
			item.setOrganName(wmlOrgan.getName());
			if(wmlOrganService.updateWmlOrgan(wmlOrgan)){
				if(wmlUserService.updateWmlUser(item)==Constant.MSG_SUCCESS){
					message= "optsuccess";
				}else{
					message= "fail";
				}
			}else{
				message="orgNull";
			}
		}
		wmlOrgan=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
		
	}
	public void addWmlOrgan() throws Exception{
		WmlUser item=new WmlUser();
		item.setId(wmlOrgan.getUserId());
		if(wmlUserService.queryWmlUser(item)!=null){
			if(wmlOrganService.addWmlOrgan(wmlOrgan)){
				message= "optsuccess";
			}else{
				message= "fail";
			}	
		}else{
			message="orgNull";
		}
		wmlOrgan=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
		
	}
}
