package com.qimou.sb.web.entity;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String communicateType = null ;//沟通方式
	private String createTime = null ;//客户创建时间
	private String customerCode = null ;//客户编号
	private String customerID = null ;//客户ID
	private String customerName = null ;//客户名称
	private int customerStat = 0 ;//客户状态：【1，正常，2，禁用】
	private String customerType = null ;//客户类型
	private String linkCellPhone = null ;//联系人手机号
	private String linkEmail = null ;//联系人邮箱地址
	private String linkMan = null ;//联系人姓名
	private String userID = null ;//用户ID【创建人：销售角色】
	private String userName = null ;//用户姓名【创建人：销售角色】
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCommunicateType() {
		return communicateType;
	}
	public void setCommunicateType(String communicateType) {
		this.communicateType = communicateType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerStat() {
		return customerStat;
	}
	public void setCustomerStat(int customerStat) {
		this.customerStat = customerStat;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	
}
