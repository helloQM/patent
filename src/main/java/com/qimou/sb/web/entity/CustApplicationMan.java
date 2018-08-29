package com.qimou.sb.web.entity;

import java.io.Serializable;

public class CustApplicationMan implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String address = null ;//详细的邮寄地址
	private String appCountry = null ;//所属国家
	private String appID = null ;//申请人pk
	private String appIDCode = null ;//申请人证件号
	private String appName = null ;//申请人名称
	private String appType = null ;//申请 人类型
	private String cityAddr = null ;//省，市，区的地址
	private String customerID = null ;//客户ID
	private int isRecord = 0 ;//是否备案【0，不备案；1，备案】
	private int recordValidYear = 0 ;//备案有效期的结束时间【年份】
	private String zipCode = null ;//邮编
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAppCountry() {
		return appCountry;
	}
	public void setAppCountry(String appCountry) {
		this.appCountry = appCountry;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAppIDCode() {
		return appIDCode;
	}
	public void setAppIDCode(String appIDCode) {
		this.appIDCode = appIDCode;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getCityAddr() {
		return cityAddr;
	}
	public void setCityAddr(String cityAddr) {
		this.cityAddr = cityAddr;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public int getIsRecord() {
		return isRecord;
	}
	public void setIsRecord(int isRecord) {
		this.isRecord = isRecord;
	}
	public int getRecordValidYear() {
		return recordValidYear;
	}
	public void setRecordValidYear(int recordValidYear) {
		this.recordValidYear = recordValidYear;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
