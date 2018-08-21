package com.qimou.sb.web.entity;

import java.io.Serializable;

public class Contract implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String contractID = null ;//合同ID【一个合同下有一个或多个任务】
	private String contractName = null ;//合同名称
	private String taskID = null ;//任务ID
	private String taskName = null ;//任务名称
	private String userID = null ;//用户ID【创建人】
	private int stat = 0 ;//任务状态【10：新建任务；11：审核通过；12：获得交底书；
                        //20：分配中；14：撰写中；15：撰写审核通过；
                        //30：递交中【形成五书】；31：获得受理通知书；32：首次驳回；33：第二次驳回；34：第三次驳回；36：国家专利局审核通过】
	private String customerID = null ;//客户ID
	private String pathJDS = null ;//交底书存放的相对路径
	private String createTime = null ;//合同创建时间
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public String getPathJDS() {
		return pathJDS;
	}
	public void setPathJDS(String pathJDS) {
		this.pathJDS = pathJDS;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
}
