package com.tool;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.pojo.WmlAdmin;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("-----------session创建成功-------------");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		System.out.println("-----------正在销毁session-------------");
		HttpSession session = sessionEvent.getSession() ;
		WmlAdmin admin=	(WmlAdmin) session.getAttribute("admin");
		if(admin!=null){
		SessionHandler.removeUserFromSessionMap(admin.getId().toString());
		System.out.println("移除成功");
		}else{
			System.out.println("没有session移除");
		}
	}

}
