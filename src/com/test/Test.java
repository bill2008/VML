package com.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.tool.TimeUtil;




public class Test {

	public static void main(String[] args) {
		
		File f = new File("D:/hekai/2b6568e3-8628-40a4-a9f9-34ec941eee00.jpg");
		f.renameTo(new File("D:/hekai/2b6568e3.jpg"));
	    
		

	}

}
