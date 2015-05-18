package com.test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.tool.TimeUtil;




public class Test {

	public static void main(String[] args) {
		
		File f = new File("D:/hekai/2b6568e3-8628-40a4-a9f9-34ec941eee00.jpg");
		f.renameTo(new File("D:/hekai/2b6568e3.jpg"));
	    
		Map mapList= new HashMap();
		
		ClassDetail c= new ClassDetail();
		c.setClassId(1);
		c.setUserName("1");
		c.setId(1);
		
		ClassDetail c1= new ClassDetail();
		c1.setClassId(2);
		c1.setUserName("2");
		c1.setId(2);
		
		
		ClassDetail c2= new ClassDetail();
		c2.setClassId(3);
		c2.setUserName("3");
		c2.setId(3);
		
		ClassDetail c3= new ClassDetail();
		c3.setClassId(4);
		c3.setUserName("4");
		c3.setId(4);
		
		mapList.put("1", c);
		mapList.put("2", c1);
		mapList.put("3", c2);
		mapList.put("4", c3);
		
		Iterator  it=mapList.entrySet().iterator();
		while(it.hasNext()){
			System.out.print(it.next() + " ");  
		}
		
		

	}

}
