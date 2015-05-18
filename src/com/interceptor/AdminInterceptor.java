package com.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminInterceptor extends AbstractInterceptor {

	
	public String intercept(ActionInvocation invocation)  {
		try{
		ActionContext ctx = invocation.getInvocationContext();

	    HttpServletRequest request = (HttpServletRequest)ctx
	      .get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
	    Map session = ctx.getSession();

	    String admin = (String)session.get("admin");
	    if ((admin != null) && (!"".equals(admin))) {
	      invocation.invoke();
	    }
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(Action.LOGIN);
	    return "login";
	}

}
