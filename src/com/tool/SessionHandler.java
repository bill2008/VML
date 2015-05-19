package com.tool;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

public class SessionHandler {
	private static final ConcurrentHashMap<String, HttpSession> sessionMap = new ConcurrentHashMap<String, HttpSession>();

	public static synchronized boolean exitsUser(String userId, HttpSession session) {
		boolean falg=false;
		HttpSession oldSession = sessionMap.get(userId);
		if (oldSession != null) {
			if (!oldSession.getId().equals(session.getId())) {
				 falg=false;
			}
		}else{
			sessionMap.put(userId, session);
			falg= true;
		}
		return falg;
	}
	
	public static boolean userExits(String userId){
		HttpSession oldSession = sessionMap.get(userId);
		if(oldSession!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public static void removeUserFromSessionMap(String userId){
		if(sessionMap.containsKey(userId)){
			sessionMap.remove(userId);
		}
		System.out.println("移除"+userId+"成功");
	}
	
}
