package com.qimou.sb.web.entity;

import java.io.Serializable;

public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int roleID = 0 ;//角色ID
	private String roleName = null ;//角色名称
	private String bak = null ;//备注说明
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getBak() {
		return bak;
	}
	public void setBak(String bak) {
		this.bak = bak;
	}
}
