package com.qimou.sb.web.entity;

import java.io.Serializable;

public class CustInventMan implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String customerID = null ;//客户ID
	private String inventCountry = null ;//发明人所属国家
	private String inventID = null ;//发明人PK
	private String inventIDCode = null ;//发明人证件号
	private String inventName = null ;//发明人名称
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getInventCountry() {
		return inventCountry;
	}
	public void setInventCountry(String inventCountry) {
		this.inventCountry = inventCountry;
	}
	public String getInventID() {
		return inventID;
	}
	public void setInventID(String inventID) {
		this.inventID = inventID;
	}
	public String getInventIDCode() {
		return inventIDCode;
	}
	public void setInventIDCode(String inventIDCode) {
		this.inventIDCode = inventIDCode;
	}
	public String getInventName() {
		return inventName;
	}
	public void setInventName(String inventName) {
		this.inventName = inventName;
	}
	
	
	
}
