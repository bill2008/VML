package com.tool;

import java.io.*;

public class GeneralStaticFile {
	public String readFile(String filepath){
		File file=new File(filepath);
		FileReader fr=null;
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		String ec="";
		String content="",line="";
		try{
		    fr=new FileReader(file);
		    ec=fr.getEncoding();
		    fis=new FileInputStream(file);
		    isr=new InputStreamReader(fis,"utf-8");
		    br=new BufferedReader(isr);
		    while((line=br.readLine())!=null){
		    content+=line+"\n";
		    }
		}catch(Exception e){}
		    return content;
		}
		
	public String changeContent(String file,String source,String aim){
		return file.replaceAll(source, aim);
	}
		
	public void writeFile(String file,String path){
		OutputStreamWriter osw=null;
		try{
		osw=new OutputStreamWriter(new FileOutputStream(path),"utf-8");
		osw.write(file);
		osw.flush();
		osw.close();
		}catch(Exception e){}
	}

}
