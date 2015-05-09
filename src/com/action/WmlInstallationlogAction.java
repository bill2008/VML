package com.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.grid.tool.GridSupport;
import com.pojo.ClinetInsertType;
import com.pojo.WmlInstallationlog;
import com.service.IWmlInstallationlogService;

public class WmlInstallationlogAction extends BaseAction {

	private IWmlInstallationlogService wmlInstallationlogService;
	private WmlInstallationlog wmlInstallationlog;
	private List<WmlInstallationlog> data;
	private String message;
	private String clientType;
	
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public WmlInstallationlog getWmlInstallationlog() {
		return wmlInstallationlog;
	}
	public void setWmlInstallationlog(WmlInstallationlog wmlInstallationlog) {
		this.wmlInstallationlog = wmlInstallationlog;
	}
	public List<WmlInstallationlog> getData() {
		return data;
	}
	public void setData(List<WmlInstallationlog> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlInstallationlogService(
			IWmlInstallationlogService wmlInstallationlogService) {
		this.wmlInstallationlogService = wmlInstallationlogService;
	}
	
	public String queryWmlInstallationlog()throws Exception{
		data=wmlInstallationlogService.queryWmlInstallationlogList(wmlInstallationlog);
		return "success";
	}
	
	public String updateWmlInstallationlog()throws Exception{

		GridSupport gridData = new GridSupport(this._gt_json);
		if(wmlInstallationlogService.updateWmlInstallationlog(gridData)){
			return "success";
		}else{
			return "fail";
		}
	}
	public void queryClientType() throws Exception{
		request.setCharacterEncoding("utf-8");
		List<ClinetInsertType> list1= wmlInstallationlogService.queryInstallClientType(clientType);
		JSONArray json = JSONArray.fromObject(list1);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print("{data:" + json.toString() + "}");
		
	}
}
