package com.qimou.sb.web.entity;

import java.io.Serializable;

/**
 SELECT CONCAT(
          'private ',
          CASE
             WHEN data_type = 'varchar' THEN 'String '
             WHEN data_type = 'timestamp' THEN 'String '
             WHEN data_type = 'date' THEN 'String '
             WHEN data_type = 'float' THEN 'float '
             WHEN data_type = 'int' THEN 'int '
             ELSE ''
          END,
          LOWER(SUBSTRING(column_name, 1, 1)),
          SUBSTRING(column_name, 2, LENGTH(column_name)),
          CASE
             WHEN data_type = 'varchar' THEN ' = null ;'
             WHEN data_type = 'timestamp' THEN ' = null ;'
             WHEN data_type = 'date' THEN ' = null ;'
             WHEN data_type = 'float' THEN ' = 0 ;'
             WHEN data_type = 'int' THEN ' = 0 ;'
             ELSE ''
          END,
          '//',
          column_comment)
          aaa
  FROM information_schema.columns
 WHERE table_schema = 'db_patent' AND table_name = 'bs_patent_user_t'
 * 
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String userID = null ;//用户ID，用作登录账号
	private String userName = null ;//用户名称
	private String pwd = null ;//登录密码
	private String userRole = null ;//用户角色
	private String userRoleShow = null ;//用户角色,只用于显示
	private String userEmail = null ;//用户邮箱地址
	private String userCellPhone = null ;//用户手机
	private int userStat = 0 ;//用户状态【1：正常；2：注销】
	private String userStatShow = "" ;//用户状态【1：正常；2：注销】,只用于显示
	private String userDepartment = null ;//用户部门
	private int userGender = 0 ;//用户性别：0，表示女；1，表示男
	private String userGenderShow = "" ;//用户性别：0，表示女；1，表示男,只用于显示
	private String createTime = null ;//创建时间
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserCellPhone() {
		return userCellPhone;
	}
	public void setUserCellPhone(String userCellPhone) {
		this.userCellPhone = userCellPhone;
	}
	public int getUserStat() {
		return userStat;
	}
	public void setUserStat(int userStat) {
		this.userStat = userStat;
	}
	public String getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	public int getUserGender() {
		return userGender;
	}
	public String getUserRoleShow() {
		return userRoleShow;
	}
	public void setUserRoleShow(String userRoleShow) {
		this.userRoleShow = userRoleShow;
	}
	public String getUserStatShow() {
		return userStatShow;
	}
	public void setUserStatShow(String userStatShow) {
		this.userStatShow = userStatShow;
	}
	public String getUserGenderShow() {
		return userGenderShow;
	}
	public void setUserGenderShow(String userGenderShow) {
		this.userGenderShow = userGenderShow;
	}
	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
