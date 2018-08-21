package com.qimou.sb.web.entity;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String customerID = null ;//客户ID
	private String userID = null ;//用户ID【创建人】
	private String customerName = null ;//客户名称
	private String inventMan = null ;//发明人姓名
	private String applicationMan = null ;//申请人姓名
	private String linkMan = null ;//联系人姓名
	private String linkCellPhone = null ;//联系人手机号
	private String linkEmail = null ;//联系人邮箱地址
	private int customerBalance = 0 ;//客户余额
	private String createTime = null ;//客户创建时间
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getInventMan() {
		return inventMan;
	}
	public void setInventMan(String inventMan) {
		this.inventMan = inventMan;
	}
	public String getApplicationMan() {
		return applicationMan;
	}
	public void setApplicationMan(String applicationMan) {
		this.applicationMan = applicationMan;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkCellPhone() {
		return linkCellPhone;
	}
	public void setLinkCellPhone(String linkCellPhone) {
		this.linkCellPhone = linkCellPhone;
	}
	public String getLinkEmail() {
		return linkEmail;
	}
	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}
	public int getCustomerBalance() {
		return customerBalance;
	}
	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
