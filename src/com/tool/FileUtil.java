package com.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.commons.io.FileUtils;

public class FileUtil {
	private static final int DEFAULT_BUFFER_SIZE = 8192;
	private static final int FILE_COPY_BUFFER_SIZE = 8192;
	
	static class Producer implements Runnable {
		private final BlockingQueue<byte[]> queue;
		private File src;
		private boolean eof;
		private InputStream in;
		
		Producer(BlockingQueue<byte[]> q, File src) {
			queue = q;
			this.src = src;
		}
		
		public void run() {
			try {
				while (!eof) {
				queue.put(produce());
			}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		System.out.println("Reader done!");
		}
		
		private InputStream getInputStream() throws FileNotFoundException {
			if (in == null) {
					in = new FileInputStream(src);
			}
			return in;
		}
		
		byte[] produce() throws FileNotFoundException, IOException {
			byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
			int len = getInputStream().read(buf);
			if (len == -1) {
				eof = true;
				return null;
			} else if (len == DEFAULT_BUFFER_SIZE) {
				return buf;
			} else {
				eof = true;
				byte[] b = new byte[len];
				System.arraycopy(buf, 0, b, 0, len);
				return b;
			}
		}
	}
	
	static class Consumer implements Runnable {
		private final BlockingQueue<byte[]> queue;
		private File dest;
		private OutputStream out;
		private boolean active;
		
		Consumer(BlockingQueue<byte[]> q, File dest) {
			queue = q;
			this.dest = dest;
		}
		
		private OutputStream getOutputStream() throws FileNotFoundException {
			if (out == null) {
			out = new FileOutputStream(dest);
			}
			return out;
		}
		
		public void run() {
			active = true;
			try {
				while (active) {
					consume(queue.take());
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println("Writer done!");
		}
		
		void consume(byte[] b) throws FileNotFoundException, IOException {
			if (b == null) {
				active = false;
				return;
			}
			if (b.length < DEFAULT_BUFFER_SIZE) {
				active = false;
			}
			getOutputStream().write(b);
		}
	}

	public static void checkFilePath(String path){
		File dir=new File(path);
		if(!dir.exists()&&!dir.isDirectory()){
			dir.mkdir();
		}
	}
	
	public static  String uploadimage(File upload,String fileurl,String fileName){
		try {
			String extName = fileName.substring(fileName.lastIndexOf("."));
			String uploadPath = ServletActionContext.getServletContext().getRealPath("/productUpload/");
			FileUtil.checkFilePath(uploadPath);
			File toFile = new File(uploadPath+extName, fileName);
			FileUtils.copyFile(upload, toFile);
			
			return uploadPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
	public static void copyFile(File src, File dest) {
		BlockingQueue<byte[]> q = new ArrayBlockingQueue<byte[]>(10);
		Producer p = new Producer(q, src);
		Consumer c = new Consumer(q, dest);
		Thread t1 = new Thread(p);
		t1.start();
		Thread t2 = new Thread(c);
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			
		}
	}

	public static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {  
        if (destFile.exists() && destFile.isDirectory()) {  
            throw new IOException("Destination '" + destFile + "' exists but is a directory");  
        }  
  
        FileInputStream fis = null;  
        FileOutputStream fos = null;  
        FileChannel input = null;  
        FileChannel output = null;  
        try {  
            fis = new FileInputStream(srcFile);  
            fos = new FileOutputStream(destFile);  
            input  = fis.getChannel();  
            output = fos.getChannel();  
            long size = input.size();  
            long pos = 0;  
            long count = 0;  
            while (pos < size) {  
                count = (size - pos) > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : (size - pos);  
                pos += output.transferFrom(input, pos, count);  
            }  
        } finally {  
            IOUtils.closeQuietly(output);  
            IOUtils.closeQuietly(fos);  
            IOUtils.closeQuietly(input);  
            IOUtils.closeQuietly(fis);  
        }  
  
        if (srcFile.length() != destFile.length()) {  
            throw new IOException("Failed to copy full contents from '" +  
                    srcFile + "' to '" + destFile + "'");  
        }  
        if (preserveFileDate) {  
            destFile.setLastModified(srcFile.lastModified());  
        }  
    }  	
	
	/**
	* @param args
	*/
/*	public static void main(String[] args) {
	copyFile(new File("d:/Testing for Non.docx"), new File("d:/1.docx"));
	} */
	
	
}  