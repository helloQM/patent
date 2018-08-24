package com.qimou.sb.web.entity;

import java.io.Serializable;

public class CustLinkMan implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String customerID = null ;//客户ID
	private String linkCellPhone = null ;//联系人手机号
	private String linkEmail = null ;//联系人邮箱
	private String linkID = null ;//联系人pk
	private String linkName = null ;//联系人名称
	private String linkQQ = null ;//联系人QQ
	private String linkTel = null ;//联系人座机号
	private String linkWeChat = null ;//联系人微信
	private String nickName = null ;//联系人昵称【称谓】
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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
	public String getLinkID() {
		return linkID;
	}
	public void setLinkID(String linkID) {
		this.linkID = linkID;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkQQ() {
		return linkQQ;
	}
	public void setLinkQQ(String linkQQ) {
		this.linkQQ = linkQQ;
	}
	public String getLinkTel() {
		return linkTel;
	}
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	public String getLinkWeChat() {
		return linkWeChat;
	}
	public void setLinkWeChat(String linkWeChat) {
		this.linkWeChat = linkWeChat;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
