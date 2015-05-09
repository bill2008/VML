package com.test;

import java.util.List;

public class ClassInfo {
	
	private int id;
	private String name;
	private List<ClassDetail> classList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ClassDetail> getClassList() {
		return classList;
	}
	public void setClassList(List<ClassDetail> classList) {
		this.classList = classList;
	}
	
	

}
