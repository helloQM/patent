package com.qimou.sb.web.entity;

import java.io.Serializable;

public class Agent implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String userID = null ;//用户ID，用作登录账号
	private String skillArea = null ;//技术领域：电子‘机械，生物医药等
	private String jobNum = null ;//营业执照号
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getSkillArea() {
		return skillArea;
	}
	public void setSkillArea(String skillArea) {
		this.skillArea = skillArea;
	}
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

}
