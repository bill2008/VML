package com.tool;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * Servlet Filter implementation class URLFilter
 */
public class URLFilter implements Filter {


	
    public URLFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {		
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
				
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; 
		/**
		 * 获得服务器名称，如：www.vml.com/
		 */
		String serverName=request.getServerName();
		int endIndex=serverName.indexOf(".");
		/**
		 * 获得二级域名
		 * 二级域名一般是一个ID, 如：www
		 */
		String secondDomainName=serverName.substring(0, endIndex);	
		
		//判断id是www还是二级域名
		if(secondDomainName.equalsIgnoreCase("www")){
			httpServletRequest.getRequestDispatcher( "/Login.jsp").forward(request, response);  
			return;
		}else{
			//挑战action并且把二级域名id传参
			httpServletRequest.getRequestDispatcher( "/wmlUser_myWmlUser.action?userId="+secondDomainName)
            .forward(request, response);    
			return;
		}		
	}
	


}
