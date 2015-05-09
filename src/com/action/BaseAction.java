package com.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;


public class BaseAction implements SessionAware, ServletRequestAware,
		ServletResponseAware {

	protected Map<String, Object> session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String _gt_json;
	protected String msg;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String get_gt_json() {
		return _gt_json;
	}

	public void set_gt_json(String gtJson) {
		_gt_json = gtJson;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


}
