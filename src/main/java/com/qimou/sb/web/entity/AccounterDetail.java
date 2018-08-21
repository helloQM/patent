package com.qimou.sb.web.entity;

import java.io.Serializable;

public class AccounterDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String customerID = null ;//客户ID
	private String userID = null ;//用户ID
	private String optionTime = null ;//操作时间
	private int moneyNum = 0 ;//资金数值【单位：元】
	private char moneyDirection = ' ' ;//资金流动方向【+：入账； -：出账】
	private String descReason = null ;//资金流动的原因描述
	
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
	public String getOptionTime() {
		return optionTime;
	}
	public void setOptionTime(String optionTime) {
		this.optionTime = optionTime;
	}
	public int getMoneyNum() {
		return moneyNum;
	}
	public void setMoneyNum(int moneyNum) {
		this.moneyNum = moneyNum;
	}
	public char getMoneyDirection() {
		return moneyDirection;
	}
	public void setMoneyDirection(char moneyDirection) {
		this.moneyDirection = moneyDirection;
	}
	public String getDescReason() {
		return descReason;
	}
	public void setDescReason(String descReason) {
		this.descReason = descReason;
	}
	
}
