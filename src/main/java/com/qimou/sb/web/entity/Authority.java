package com.qimou.sb.web.entity;

import java.io.Serializable;

public class Authority implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id = 0 ;//目录ID
	private int pId = 0 ;//父目录ID
	private String name = null ;//目录名称
	private String path = null ;//目录相对路径
	private int open = 0 ;//是否展开标识：0标识不展开，1标识展开
	private String description = null ;//权限功能介绍
	private String avalue = null ;
	
	public String getAvalue() {
		return avalue;
	}
	public void setAvalue(String avalue) {
		this.avalue = avalue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	
}
